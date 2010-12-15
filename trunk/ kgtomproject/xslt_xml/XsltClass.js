function XsltClass(xmlName, xslName) {
 var processor;
 var isReady=false;
 
 this.output=output;
 this.setParam=setParam;
 this.setXsl=setXsl;
 this.setXml=setXml;
 this.loadXmlStr=loadXmlStr;

 var xmlDoc=new ActiveXObject("MSXML2.FreeThreadedDOMDocument.3.0");
 xmlDoc.async=false;
 var xslTemplate=new ActiveXObject("MSXML2.XSLTemplate.3.0");
 
 function loadXmlStr(str) {
  xmlDoc.loadXML(str);
 }
 
 function getProcessor(transformName){
  var xslDoc=new ActiveXObject("MSXML2.FreeThreadedDOMDocument.3.0");
  xslDoc.async=false;
  xslDoc.load(transformName);
  xslTemplate.stylesheet=xslDoc;
  xslProcessor=xslTemplate.createProcessor();
  return(xslProcessor);
 }
 
 function transformData(xmlDoc, processor, response){
  if(response) processor.output=response;
  processor.input=xmlDoc;
  processor.transform();
  return(processor.output);
 }
 
 function output(response) {
  result=null;
  if(isReady) {
   result=transformData(xmlDoc,processor,response);
  }
  return(result);
 }
 
 function setParam(param, value) {
  processor.addParameter(param, value);
 }
 
 function setXsl(aXslName) {
  processor=getProcessor(aXslName);
  isReady=true;
 }

 function setXml(aXmlName) {
  xmlDoc.load(aXmlName);
 }

 function init(aXmlName, aXslName){
  if(aXmlName!=undefined && aXslName!=undefined) {
   xmlDoc.load(aXmlName);
   processor=getProcessor(aXslName);
   isReady=true;
  }
 }
 
 init(xmlName, xslName);
}


//---------  XsltClass.js  结束 -----------



