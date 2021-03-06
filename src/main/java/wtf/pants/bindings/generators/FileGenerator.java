package wtf.pants.bindings.generators;

import wtf.pants.bindings.mappings.ClassMap;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public interface FileGenerator {
    String fileName();

    void generate(PrintWriter pw, List<ClassMap> classMaps);

    default void generateFile(List<ClassMap> classMaps) {
        final PrintWriter classIndexPrintWriter = createPrintWriter();
        if (classIndexPrintWriter == null) {
            System.out.println("Failed on generator " + getClass().getName());
            return;
        }
        generate(classIndexPrintWriter, classMaps);
        classIndexPrintWriter.flush();
        classIndexPrintWriter.close();
    }

    default PrintWriter createPrintWriter() {
        try {
            final File file = new File("target/" + fileName());

            if (file.exists() && !file.delete()) {
                System.out.println("Failed to delete existing file at " + file.getAbsolutePath());
                return null;
            } else if (!file.exists() && !file.createNewFile()) {
                System.out.println("Failed to create new file at " + file.getAbsolutePath());
                return null;
            }

            return new PrintWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
