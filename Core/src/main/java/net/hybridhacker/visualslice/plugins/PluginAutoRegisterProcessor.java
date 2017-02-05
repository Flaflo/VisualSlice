package net.hybridhacker.visualslice.plugins;

import com.google.auto.service.AutoService;
import com.google.common.collect.Sets;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 *
 */
@AutoService(Processor.class)
public class PluginAutoRegisterProcessor extends AbstractProcessor {
    
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Sets.newHashSet(AutoRegister.class.getCanonicalName());
    }
    
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
    
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (final Element element : roundEnv.getElementsAnnotatedWith(AutoRegister.class)) {
            if (element instanceof TypeElement) {
                final TypeElement typeElement = (TypeElement) element;
                
                try {
                    final String fileName = AutoRegister.PLUGIN_META_FILE;
                    final FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", fileName);
                    
                    try (Writer writer = fileObject.openWriter()) {
                        writer.write(typeElement.getQualifiedName().toString());
                    }
                } catch (final IOException ex) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.getMessage());
                }
            }
        }
        
        return true;
    }
}
