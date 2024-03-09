package kr.projects.trace

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class ParentService(
    private val childService: ChildService,
) {

    private val log = LoggerFactory.getLogger(ParentService::class.java)

    @Async(AsynchronousConfiguration.ASYNC_TASK_EXECUTOR_BEAN_NAME)
    fun startParentTask(traceId: String) {

        val parentThreadName = Thread.currentThread().name
        log.info("Parent task started with traceId: $traceId on thread: $parentThreadName")

        childService.startChildTask(traceId)

        Thread.sleep(1000)

        log.info("Parent task completed with traceId: $traceId on thread: $parentThreadName")
    }
}
