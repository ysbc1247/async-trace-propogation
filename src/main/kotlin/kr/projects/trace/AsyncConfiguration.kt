package kr.projects.trace

import io.micrometer.context.ContextSnapshot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor

@Configuration
class AsynchronousConfiguration: AsyncConfigurer {
    @Bean
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor = ThreadPoolTaskExecutor().apply {
        corePoolSize = CORE_POOL_SIZE
        maxPoolSize = MAX_POOL_SIZE
        queueCapacity = QUEUE_CAPACITY
        setThreadNamePrefix("async-")
        setTaskDecorator(taskDecorator())
    }

    @Bean(ASYNC_TASK_EXECUTOR_BEAN_NAME)
    fun asyncTaskExecutor(delegate: ThreadPoolTaskExecutor): DelegatingSecurityContextAsyncTaskExecutor {
        return DelegatingSecurityContextAsyncTaskExecutor(delegate)
    }

    companion object {
        const val ASYNC_TASK_EXECUTOR_BEAN_NAME = "asyncTaskExecutor"
        const val CORE_POOL_SIZE = 10
        const val MAX_POOL_SIZE = 50
        const val QUEUE_CAPACITY = 50
    }
}