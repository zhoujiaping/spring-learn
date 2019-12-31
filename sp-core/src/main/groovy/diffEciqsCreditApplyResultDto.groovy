def fields = EciqsCreditApplyResultDto.declaredFields.collect{
    it.name
}
def apiFields = """
apply_no
id_number
approve_rlt
refuse_code
refuse_reason
limit
rate
credit_type
risk_level
decision_time

""".trim().split(/\s+/).collect{
    def parts = it.split(/_/)
    if(parts.length==1){
        return it
    }
    parts[0]+parts[1..-1].collect{
        part->
            part[0].toUpperCase()+part[1..-1]
    }.join('')
}
def diff = apiFields - fields
diff.sort()
println diff
def diff2 = fields - apiFields
diff2.sort()
println diff2