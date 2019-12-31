/***
* 该文件用于将word格式的合同文件转换为html格式的文件。
* 步骤：
* 1、用office打开word
* 2、另存为 网页（此时的文件已经是html格式，但是存在一些问题，比如体积大，产生了一些额外的文件，会导致无法签章）
* 3、将
<script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script src="trans.js"></script>
拷贝到产生的html文件中。
* 4、在浏览器中打开html文件
* 5、f12，查看控制台输出
* 6、拷贝输出的内容，保存即可
*
* 注意：如果文档中有比较特殊的内容，比如图片，需要手动处理。 
*
* 解决访问本地文件跨域问题：启动chrome的时候添加参数 --allow-file-access-from-files
*
*/
let headContent = `
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<title>{{title}}</title>
<style>
    html,
    body {
        width: 100%;
        height: 100%;
        margin: 0;
        box-sizing: border-box;
        font-size: 0.75em;
        line-height: 1.5;
    }
    html{
        padding: 0;
    }
    body{
        padding: 1em;
    }
    .text-indent {
        text-indent: 2em;
    }
    .text-underline{
        text-decoration:underline;
    }
    .border-collapse{
        border-collapse: collapse;
    }
</style>
`
let head = `
<head>
    {{headContent}}
</head>
`
let content = ``
let bodyContent = `
{{h2}}
{{content}}
`
let body = `
<body>
    {{bodyContent}}
</body>
`
let html = `
<!DOCTYPE html>
<html>
	{{head}}
	{{body}}
</html>
`
let filename = ''
/**
保留对其方式、加粗、下划线、段落、缩进
https://www.cnblogs.com/xiaohuochai/p/5785189.html
元素节点            　　Node.ELEMENT_NODE(1)
属性节点            　　Node.ATTRIBUTE_NODE(2)
文本节点            　　Node.TEXT_NODE(3)
CDATA节点             Node.CDATA_SECTION_NODE(4)
实体引用名称节点    　　 Node.ENTRY_REFERENCE_NODE(5)
实体名称节点        　　Node.ENTITY_NODE(6)
处理指令节点        　　Node.PROCESSING_INSTRUCTION_NODE(7)
注释节点            　 Node.COMMENT_NODE(8)
文档节点            　 Node.DOCUMENT_NODE(9)
文档类型节点        　　Node.DOCUMENT_TYPE_NODE(10)
文档片段节点        　　Node.DOCUMENT_FRAGMENT_NODE(11)
DTD声明节点            Node.NOTATION_NODE(12)
*/
const promises = []
const eleTranser = {
	'V:IMAGEDATA':node=>{
		let parent = $(node).parent()
		let width = parent[0].style.width
		let height = parent[0].style.height		
		parent.html(`<img src="${node.attributes.src.nodeValue}" width="${width}" height="${height}"/>`)
		return eleTranser['IMG'](parent.children()[0])
	},
	'IMG':node=>{
		if(!node.complete){
			let id = +new Date() + '' + Math.random()
			promises.push(new Promise((resolve,reject)=>{
					node.onload = ()=>{
						let param = {}
						param[id] = getBase64Image(node)
						content = content.format(param)
						resolve()
					}
					node.onerror = err =>{
					    alert('################')
						console.error(err)
						reject()
					}
			}))
			return `<img src="{{${id}}}"></img>`
		}else{
			let base64 = getBase64Image(node)
			return `<img src="${base64}"></img>`
		}
	},
	'STYLE':node=>'',
	'SCRIPT':node=>'',
	'TD':node=>{
		let name = node.nodeName
		//所有属性
		let attrs = []
		
		//其他属性
		attrs.push(`align='center'`)

		let colSpan = node.colSpan
		if(colSpan && colSpan!=''){
			attrs.push(`colspan='${colSpan}'`)
		}
		
		let childText = $(node).text()
		let lowerName = name.toLowerCase()
		if(attrs.length>0){
			return `<${lowerName} ${attrs.join(' ')}>${childText}</${lowerName}>`
		}else{
			return `<${lowerName}>${childText}</${lowerName}>`
		}
	},
	'TR':node=>{
		if($.trim($(node).text())==''){
			return ''
		}
		return eleTranser['DEFAULT'](node)
	},
	'TABLE':node=>{
		let name = node.nodeName
		//所有属性
		let attrs = []
		
		//样式
		let classList = []

		let borderCollapse = node.style.borderCollapse
		if(borderCollapse && borderCollapse!=''){
			classList.push(`border-collapse`)
		}
		
		if(classList.length>0){
			attrs.push('class="'+classList.join(' ')+'"')
		}
		
		let border = node.border
		if(border && border!=''){
			attrs.push(`border="${border}"`)
		}
		
		//其他属性
		let align = node.align
		if(align && align!=''){
			attrs.push(`align='${align}'`)
		}
		
		let childText = Array.from(node.childNodes).map(child=>{
			return nodeTranser[child.nodeType](child)
		}).join('')
		let lowerName = name.toLowerCase()
		if(attrs.length>0){
			return `<${lowerName} ${attrs.join(' ')}>${childText}</${lowerName}>`
		}else{
			return `<${lowerName}>${childText}</${lowerName}>`
		}
	},
	'DEFAULT':node=>{
		let name = node.nodeName
		if(name.indexOf(':')>-1){
			let childText = Array.from(node.childNodes).map(child=>{
				return nodeTranser[child.nodeType](child)
			}).join('')
			//去掉&nbsp;
			childText = childText.split(/\&nbsp;/).join('')
			return $.trim(childText)
		}
		//所有属性
		let attrs = []
		
		//样式
		let classList = []
		
		let textIndent = node.style.textIndent
		if(textIndent && textIndent!='' && !/'[-0].*'/igm.test(textIndent)){
			classList.push('text-indent')
		}
		
		let textDecoration = node.style.textDecoration
		if(textDecoration && textDecoration.indexOf('underline')>-1){
			classList.push('text-underline')
		}
		
		if(classList.length>0){
			attrs.push('class="'+classList.join(' ')+'"')
		}
		
		//其他属性
		let align = node.align
		if(align && align!=''){
			attrs.push(`align='${align}'`)
		}
		
		let childText = Array.from(node.childNodes).map(child=>{
			return nodeTranser[child.nodeType](child)
		}).join('')
		//去掉&nbsp;
		childText = childText.split(/\&nbsp;/).join('')
		if($.trim(childText) == ''){
			return ''
		}
		let lowerName = name.toLowerCase()
		if(attrs.length>0){
			return `<${lowerName} ${attrs.join(' ')}>${childText}</${lowerName}>`
		}else{
			return `<${lowerName}>${childText}</${lowerName}>`
		}
	}
}
const nodeTranser = [
	null,
	node=>{
		let transer = eleTranser[node.nodeName]
		if(!transer){
			transer = eleTranser['DEFAULT']
		}
		return transer(node)
	},
	node=>'',
	node=>node.nodeValue,
	node=>'',
	node=>'',
	node=>'',
	node=>'',
	node=>''
]
function trans(){
	let doc = $(document.body)
	let h2 = doc.find('p').first()
	h2.remove()
	filename = h2.text()+'.html'
	headContent = headContent.format({title:h2.text()})
	bodyContent = bodyContent.format({h2:`<h2 align="center">${h2.text()}</h2>`})
	let eleList = doc.children()
	let arr = eleList.map((i,it)=>{
		return nodeTranser[it.nodeType](it)
	})
	content = Array.from(arr).join('')
	//var m = rst?+str.substr(rst.index,rst[1].length):0
	Promise.all(promises).then(()=>{
		/*$.post('http://localhost:8989/crypt-db-client/magic',{text:html,filename:filename},null,'json').done(resp=>{
			window.open('http://localhost:8989/crypt-db-client/download?filename='+resp.filename,true)
		})*/
		let reg = /(第.{1,2}?条)/igm
		content = content.replace(reg,'$1&nbsp;')

        html = html.replace(/\r|\n|\t/,'')+'\n'
        head = head.replace(/\r|\n|\t/,'')+'\n'
        headContent = headContent.replace(/\r|\n|\t/,'')+'\n'
        body = body.replace(/\r|\n|\t/,'')+'\n'
        bodyContent = bodyContent.replace(/\r|\n|\t/,'')+'\n'
        content = content.replace(/\r|\n|\t/,'')+'\n'

		bodyContent = bodyContent.format({content})
        head = head.format({headContent})
        body = body.format({bodyContent})
        html = html.format({head,body})
        console.info(html)
        let newWin = window.open(`trans.html?filename=${filename}`,'_blank')
        if(newWin == null){
            alert('打开新窗口被拦截，可以在控制台查看结果')
        }else{
            newWin.onload = ()=>{
                $(newWin.document.head).html(headContent)
                $(newWin.document.body).html(bodyContent)
            }
        }

        //$(document.head).html(headContent)
        //$(document.body).html(bodyContent)

	})
}
//程序入口
$(trans)

/**格式化字符串*/
String.prototype.format = function(params){
	let reg = null
	let res = this
	$.each(params,function(index,ele){
		reg = new RegExp("\\{\\{" + index + "\\}\\}", "igm")
		res = res.replace(reg,ele)
	})
	return res
}
 
function getBase64Image(img) {  
     var canvas = document.createElement("canvas")
     canvas.width = $(img).width()
     canvas.height = $(img).height()
     var ctx = canvas.getContext("2d")
     ctx.drawImage(img, 0, 0, img.width, img.height)
     var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase()
     var dataURL = canvas.toDataURL("image/"+ext)
     return dataURL
}