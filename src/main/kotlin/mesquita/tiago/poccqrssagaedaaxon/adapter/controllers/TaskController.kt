package mesquita.tiago.poccqrssagaedaaxon.adapter.controllers

import mesquita.tiago.poccqrssagaedaaxon.command.CreateTaskCommand
import mesquita.tiago.poccqrssagaedaaxon.domain.task.Task
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class TaskController(
    private val commandGateway: CommandGateway
) {
    
    @RequestMapping(value = ["/task"], method = [RequestMethod.GET])
    fun getTask(): String {
        return "Get Task"
    }
    
    @RequestMapping(value = ["/task"], method = [RequestMethod.POST])
    fun createTask(
        @Valid @RequestBody task: Task
    ): String {
        val createTaskCommand = CreateTaskCommand(
            taskId = UUID.randomUUID().toString(),
            description = task.description
        )
        
        val returnedValue = try {
            commandGateway.sendAndWait<String>(createTaskCommand)
        } catch (e: Exception) {
            e.localizedMessage
        }
        
        return returnedValue
    }
    
    @RequestMapping(value = ["/task"], method = [RequestMethod.PUT])
    fun updateTask(): String {
        return "Update Task"
    }
    
    @RequestMapping(value = ["/task"], method = [RequestMethod.DELETE])
    fun deleteTask(): String {
        return "Delete Task"
    }
    
}