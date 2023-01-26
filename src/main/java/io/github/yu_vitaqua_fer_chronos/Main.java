package io.github.yu_vitaqua_fer_chronos;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static final Engine engine = Engine.newBuilder().option("engine.WarnInterpreterOnly", "false").build();

    public static void main(String[] args) {
        System.out.println("Hello world from the Java bootstrapper!");

        Context context = Context.newBuilder("js")
                .engine(Engine.create()) // Show the warning at least once, so the user is aware that the performance is
                  // not as good as it could be
                .allowAllAccess(true)
                .allowExperimentalOptions(true)
                .option("js.nashorn-compat", "true")
                .option("js.commonjs-require", "true")
                .option("js.ecmascript-version", "2022")
                .option("js.commonjs-require-cwd", ".")
                .build();

        try {
            context.eval(Source.newBuilder("js", Paths.get("jsmain/index.js").toFile()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}