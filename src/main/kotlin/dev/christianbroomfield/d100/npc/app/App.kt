package dev.christianbroomfield.d100.npc.app

import dev.christianbroomfield.d100.npc.D100Configuration
import dev.christianbroomfield.d100.npc.resource.PingResource
import mu.KotlinLogging
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.HttpTransaction
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.ResponseFilters
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.routes

private val log = KotlinLogging.logger {}
object App {
    operator fun invoke(config: D100Configuration): HttpHandler {
        val pingResource = PingResource()

        return assembleFilters(config).then(
            routes(
                "/ping" bind pingResource()
            )
        )
    }

    private fun assembleFilters(config: D100Configuration): Filter {
        val filter = when {
            config.debug -> DebuggingFilters
                .PrintRequestAndResponse()
                .then(ServerFilters.CatchLensFailure)

            else -> ServerFilters.CatchLensFailure
        }

        return filter
            .then(ResponseFilters.ReportHttpTransaction { tx: HttpTransaction ->
                log.info { "${tx.request.uri} ${tx.response.status}; took ${tx.duration.toMillis()}ms" }
            })
    }
}
