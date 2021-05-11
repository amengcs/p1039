package p1039;

import java.io.File;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.asciidoctor.ast.Document;

public class App 
{

  private Asciidoctor asciidoctor;

  public App()
  {
    this.asciidoctor = Asciidoctor.Factory.create();
    this.asciidoctor.requireLibrary("asciidoctor-diagram");
  }

  public void render(File document, File dest)
  {

    AttributesBuilder ab = AttributesBuilder.attributes();
    ab.allowUriRead(true);

    OptionsBuilder ob = OptionsBuilder.options();
    ob.attributes(ab);
    ob.baseDir(document.getParentFile());
    ob.sourceDir(document.getParentFile());
    ob.safe(SafeMode.UNSAFE);
    ob.toFile(true);
    ob.toDir(dest);
    ob.destinationDir(dest);
    ob.mkDirs(true);
    ob.backend("pdf");

    // This works.
    //asciidoctor.convertFile(document, ob.build());

    // This throws a missing method exception: rindex
    Document ast = asciidoctor.loadFile(document, ob.build());

  }

  public static void main(String[] args) throws Exception
  {

    File pwd = (new File(System.getProperty("user.dir"))).getCanonicalFile();

    App app = new App();

    File doc = new File(pwd, "test.adoc");
    File dest = pwd;
    app.render(doc, dest);

  }


}
