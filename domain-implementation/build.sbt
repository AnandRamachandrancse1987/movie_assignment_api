val slickVersion = "3.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.google.inject" % "guice" % "4.0"
)

/*lazy val slick: TaskKey[Seq[File]] = taskKey[Seq[File]]("Generate code from the DB tables")

slick := {
  val outputDir = (baseDirectory.value / "src/main/scala").getPath

  val url = "jdbc:postgresql://127.0.0.1:5432/diesel_assignment?user=postgres&password=root"
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "slick.jdbc.PostgresProfile"
  val pkg = "io.github.vjames19.finatraexample.blog.models"
  toError((runner in Compile).value.run("slick.codegen.SourceCodeGenerator", (dependencyClasspath in Compile).value.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), streams.value.log))
  val fname = s"$outputDir/com/db/service/Tables.scala"
  Seq(file(fname))
}*/

flywayUrl := "jdbc:postgresql://127.0.0.1:5432/diesel_assignment_02"
flywayUser := "postgres"
flywayPassword := "root"
flywayBaselineOnMigrate := true
flywayLocations := Seq("classpath:db/migration")