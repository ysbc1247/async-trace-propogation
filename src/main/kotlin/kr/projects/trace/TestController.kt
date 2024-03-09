package kr.projects.trace

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

@RestController
class TraceController (private val parentService: ParentService) {

    private val log = LoggerFactory.getLogger(TraceController::class.java)

    @GetMapping("/test-trace")
    fun testTraceId(): String {
        val traceId = java.util.UUID.randomUUID().toString()
        log.info("Starting asynchronous job with traceId: $traceId")
        parentService.startParentTask(traceId)
        return "Asynchronous job started with traceId: $traceId"
    }
}
