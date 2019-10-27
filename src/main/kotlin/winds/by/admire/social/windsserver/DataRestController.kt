package winds.by.admire.social.windsserver

import org.springframework.web.bind.annotation.*
import java.nio.file.Paths

@RestController
internal class DataRestController(
        private val personsRepository: PersonsRepository,
        private val projectsRepository: ProjectsRepository,
        private val plansRepository: PlansRepository) {

    @RequestMapping("/get-uri")
    fun getUri(): String {
        return Paths.get("upload-dir").toString() + " " + Paths.get("upload-dir").toAbsolutePath().toString()
    }

    @RequestMapping("/get-all-data")
    fun getAllData(): AllDataObject {
        val allDataObject = AllDataObject()
        allDataObject.persons = personsRepository.findAll()
        allDataObject.projects = projectsRepository.findAll()
        allDataObject.plans = plansRepository.findAll()
        return allDataObject
    }

    @GetMapping("/get-all-persons")
    fun getAllPersons(): List<PersonObject> {
        return personsRepository.findAll()
    }

    @PostMapping("/add-person")
    fun addPerson(@RequestBody personObject: PersonObject): PersonObject {
        return if(personObject.id != null) personsRepository.save(personObject)
        else
            personsRepository.save(
                    PersonObject(
                            personObject.name,
                            personObject.who,
                            personObject.profile,
                            personObject.tel,
                            personObject.email,
                            personObject.avatar,
                            personObject.status
                    )
            )
    }

    @GetMapping("/remove-person/{id}")
    fun removePerson(@PathVariable id: Long?) {
        personsRepository.deleteById(id!!)
    }

    @GetMapping("/get-all-projects")
    fun getAllProjects(): List<ProjectObject> {
        return projectsRepository.findAll()
    }

    @PostMapping("/add-project")
    fun addProject(@RequestBody projectObject: ProjectObject): ProjectObject {
        return if(projectObject.id != null) projectsRepository.save(projectObject)
        else
            projectsRepository.save(
                    ProjectObject(
                            projectObject.title,
                            projectObject.workers,
                            projectObject.platform,
                            projectObject.start,
                            projectObject.end,
                            projectObject.avatar
                    )
            )
    }

    @GetMapping("/remove-project/{id}")
    fun removeProject(@PathVariable id: Long?) {
        projectsRepository.deleteById(id!!)
    }

    @GetMapping("/get-all-plans")
    fun getAllPlans(): List<PlanObject> {
        return plansRepository.findAll()
    }

    @PostMapping("/add-plan")
    fun addPlan(@RequestBody planObject: PlanObject): PlanObject {
        return if(planObject.id != null) plansRepository.save(planObject)
        else
            plansRepository.save(
                    PlanObject(
                            planObject.project_id,
                            planObject.time
                    )
            )
    }

    @GetMapping("/remove-plan/{id}")
    fun removePlan(@PathVariable id: Long?) {
        plansRepository.deleteById(id!!)
    }

    @GetMapping("/clear-repository?server.7winds")
    fun clearRepository(): String {
        personsRepository.deleteAll()
        projectsRepository.deleteAll()
        plansRepository.deleteAll()
        return "well"
    }
}