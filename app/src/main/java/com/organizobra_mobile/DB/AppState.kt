package com.organizobra_mobile.DB

object AppState {
    val projectMap = HashMap<String, Project>()
    val projCodeList: MutableList<String> = mutableListOf();

    fun save(value: Project) {
        projectMap[value.code] = value
    }

    fun get(key: String): Any? {
        return projectMap[key]
    }
}


class Project(
    var id: Int = AppState.projCodeList.size + 1,
    var name: String,
    var address: String,
    var customer: String,
    var manager: String,
    var beginDate: String,
    var employeesList: MutableList<Employee> = mutableListOf()
    ) {

    var code: String = firstWord(this.name);

    private fun firstWord(text: String) = text.substringBefore(" ");

    fun getCard(): Card {
        return Card(this.name, this.address, this.manager + ' ' + this.beginDate)
    }

    fun addEmployee(
        name: String,
        job: String,
        costType: String,
        costValue:Double,
        payType: String,
        payDoc: String,
        projCode: String): Unit {
        val employee = Employee(
            //REFATORAR
            id = this.employeesList.size + 1,
            name = name,
            job = job,
            costType = costType,
            costValue = costValue,
            payType = payType,
            payDoc = payDoc,
            projCode = projCode
        );
        this.employeesList.add(employee)
    }

}
class Employee(
    var id: Int,
    var name: String,
    var job: String,
    var costType: String,
    var costValue: Double,
    var payType: String,
    var payDoc: String,
    var projCode: String
){
    fun getCard(): EmployeeCard {
        return EmployeeCard(
            this.name,
            this.job,
            this.costType + ": " + this.costValue,
            this.projCode)
    }
}
data class Card(val name: String, val address: String, val subtitle: String);
data class EmployeeCard(val name: String, val job: String, val subtitle: String, val project:String);