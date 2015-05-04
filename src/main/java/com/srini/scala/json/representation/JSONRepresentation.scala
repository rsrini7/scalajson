package com.srini.scala.json.representation

abstract class JSON
case class JSeq(elems: List[JSON]) extends JSON
case class JObj(bindings: Map[String, JSON]) extends JSON
case class JNum(num: Double) extends JSON
case class JStr(str: String) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

object JSONRepresentation {
  
  def main(args: Array[String]){
    val data = JObj (Map(
        "firstName" ->   JStr("Srini"),
        "lastName" -> JStr("Raghu"),
        "address" -> JObj (Map(
            "area" -> JStr("LBS Nagar")
            )),
         "phonenumbers" -> JSeq(List (
           JObj(Map( 
               "type" -> JStr("Home"),
               "phone1" -> JNum(123456789)
            )),
            JObj(Map( 
               "type" -> JStr("Work"),
               "phone2" -> JNum(987654321)
            ))
         ))
    ))
    
    println(show(data));
  }
  
  def show(json : JSON):String = json match {
    case JSeq(elems) => "[" + (elems map show mkString ", ") + "]"
    case JObj(bindings) => 
      val assocs = bindings map { case (key, value) => "\"" + key + "\": " + show(value) } 
      "{" + (assocs mkString ", ") + "}"
    case JNum(num) => num.toString
    case JStr(str) => '\"' + str + '\"'
    case JBool(b) => b.toString
    case JNull => "null"
  }
  
}