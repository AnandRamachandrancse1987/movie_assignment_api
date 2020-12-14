val slickVersion = "3.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.google.inject" % "guice" % "4.0"
)

flywayUrl := "jdbc:postgresql://127.0.0.1:5432/diesel_assignment"
flywayUser := "postgres"
flywayPassword := "root"
flywayBaselineOnMigrate := true
flywayLocations := Seq("classpath:db/migration")