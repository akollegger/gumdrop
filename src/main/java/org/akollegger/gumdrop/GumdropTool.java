package org.akollegger.gumdrop;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.http.client.fluent.Content;

import java.io.FileInputStream;
import java.io.IOException;

public class GumdropTool {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser();
        ArgumentAcceptingOptionSpec<String> fileArg = parser.accepts("file").withRequiredArg();

        OptionSet options = parser.parse( args );

        int exitCode = 0;

        if (args.length == 0) {
            // start gui
            GumdropApp app = new GumdropApp();
            app.display();
        } else {
            GumdropPost dropPost = new GumdropPost();
            if (options.has(fileArg)) {
                for (String filename : options.valuesOf(fileArg)) {
                    try {
                        dropPost.postQueries(new FileInputStream(filename));
                    } catch (IOException e) {
                        System.err.println("Failed to post \"" + filename + "\", because " + e.getLocalizedMessage());
                        exitCode = 1;
                    }
                }
            }

            StringBuilder directQueryBuilder = new StringBuilder();
            for (String remainingArg : options.nonOptionArguments()) {
                directQueryBuilder.append(remainingArg);
            }
            String directQuery = directQueryBuilder.toString().trim();
            if (directQuery.length() > 0) {
                Content result = null;
                try {
                    result = dropPost.postQuery(directQuery);
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                    exitCode = 2;
                }
                System.out.println(result.asString());
            }
            System.exit(exitCode);
        }
    }
}