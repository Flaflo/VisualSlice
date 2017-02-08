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
 * A processor for {@link AutoRegister} to auto-generate the file for the plugin
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
    
    /**
     * Loops to all found {@link AutoRegister} annotations and creates a resource file named {@value AutoRegister#PLUGIN_META_FILE}. So
     * there cannot be more than one register annotation per plugin archive since this would result in the resource file overwriting
     * itself.
     *
     * @param annotations A set of found annotations processed by this processor
     * @param roundEnv    the processors round environment containing the context of current processing round
     *
     * @return definitely true, since all annotations are handled within the first call of this processor
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (final Element element : roundEnv.getElementsAnnotatedWith(AutoRegister.class)) {
            if (element instanceof TypeElement) {
                final TypeElement typeElement = (TypeElement) element;
    
                if (!processingEnv.getTypeUtils().isAssignable(typeElement.asType(), processingEnv.getElementUtils().getTypeElement
                        (SlicePlugin.class.getCanonicalName()).asType())) {
                    throw new IllegalPluginDefinitionException(typeElement.asType().toString() + " does not implement " + SlicePlugin
                            .class.getCanonicalName());
                }
                
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
