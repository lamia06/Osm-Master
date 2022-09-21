description = "Core module"

dependencies {
  api("ch.qos.logback:logback-classic:${property("version.logback")}")
  api("com.jolbox:bonecp:${property("version.bonecp")}")

  api("org.apache.xmlgraphics:batik-svggen:${property("version.batik")}")
  api("org.apache.xmlgraphics:batik-dom:${property("version.batik")}")
  api("org.apache.xmlgraphics:batik-transcoder:${property("version.batik")}")
  api("org.apache.xmlgraphics:xmlgraphics-commons:${property("version.xmlgraphics")}")
  api("org.apache.xmlgraphics:batik-util:${property("version.batik")}")
  api("org.slf4j:jcl-over-slf4j:${property("version.slf4j")}")
  api("org.springframework:spring-context")
}

