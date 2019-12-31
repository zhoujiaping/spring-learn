def fields = EciqsCreditApplyReq.declaredFields.collect{
    it.name
}
def apiFields = """
id_number
product
cust_channel
device_id
black_box
apply_no
update_flag
token_key
ip_address
query_cert_type
platform
approve_env
token_id
event_occur_time
account_name
account_mobile
autho_file_url
is_realtime
query_reason
is_iron_man 
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