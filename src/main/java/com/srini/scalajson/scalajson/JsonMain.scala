package com.srini.scalajson.scalajson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.DeserializationFeature

/**
 * @author SrinivasanRag
 * https://raw.githubusercontent.com/oschrenk/scala-jackson/master/src/test/scala/com/acme/jackson/TestJsonDeserialize.scala
 *
 */

object JsonUtil {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def fromJson[T](json: String, clazz: Class[T]): T = {
    mapper.readValue(json, clazz)
  }

}

case class MySequence(items: Seq[String])

case class MyScalaIterable(items: Iterable[String])

case class MyJavaIterable(items: java.lang.Iterable[String])

object JsonMain {

  def main(args: Array[String]) {

    val JsonArray = "{\"items\":[\"one\",\"two\"]}"

    // works in 2.1.2, 2.2, 2.3
    println(JsonUtil.fromJson(JsonArray, classOf[MySequence]))

    // fails in 2.1.2
    // works in 2.2, 2.3
    println(JsonUtil.fromJson(JsonArray, classOf[MyJavaIterable]))

    // fails in 2.1.2, 2.2, 2.3
    println(JsonUtil.fromJson(JsonArray, classOf[MyScalaIterable]))
  }

}