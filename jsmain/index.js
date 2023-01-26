var Paths = Java.type("java.nio.file.Paths");

var Context = Java.type("org.graalvm.polyglot.Context");
var Engine = Java.type("org.graalvm.polyglot.Engine");
var Source = Java.type("org.graalvm.polyglot.Source");

var mainEngine = Java.type("io.github.yu_vitaqua_fer_chronos.Main").engine;

var context = Context.newBuilder("js")
    .engine(mainEngine) // Show the warning at least once, so the user is aware that the performance is
      // not as good as it could be
    .allowAllAccess(true)
    .allowExperimentalOptions(true)
    .option("js.nashorn-compat", "true")
    .option("js.commonjs-require", "true")
    .option("js.ecmascript-version", "2022")
    .option("js.commonjs-require-cwd", ".")
    .build();

console.log("Hello from the JS core!");

context.eval(Source.newBuilder("js", Paths.get("example.js").toFile()).build());