package kr.projects.trace

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class ChildService {
    private val log = LoggerFactory.getLogger(ChildService::class.java)

    @Async(AsynchronousConfiguration.ASYNC_TASK_EXECUTOR_BEAN_NAME)
    fun startChildTask(traceId: String) {
        val childThreadName = Thread.currentThread().name
        val traceId = Thread.currentThread().stackTrace
        log.info("Child task started with traceId: $traceId on thread: $childThreadName")

        Thread.sleep(1000)

        log.info("Child task completed with traceId: $traceId on thread: $childThreadName")
    }
}
