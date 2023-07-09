package com.carlos.usuarios.beusers.controllers;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carlos.usuarios.beusers.clases.FileUtil;
import com.carlos.usuarios.beusers.htmlForms.QuizPreguntaForm;
import com.carlos.usuarios.beusers.models.entities.QuizPregunta;
import com.carlos.usuarios.beusers.services.QuizPreguntaService;

@RestController
@RequestMapping("/quiz/pregunta")
public class QuizPreguntaController {

    @Autowired
    private QuizPreguntaService service;

    @GetMapping
    public List<QuizPregunta> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<QuizPregunta> quizOptional = service.findById(id);
        
        if(quizOptional.isPresent()){
            return ResponseEntity.ok(quizOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute QuizPreguntaForm form){
        QuizPregunta quizPregunta = new QuizPregunta();;
        quizPregunta.setPregunta1(form.getPregunta1());
        quizPregunta.setPregunta2(form.getPregunta2());
        quizPregunta.setPregunta3(form.getPregunta3());
        quizPregunta.setPregunta4(form.getPregunta4());
        quizPregunta.setQuiz(form.getQuiz());
        quizPregunta.setRta(form.getRta());
        
        if (form.getArchivoAdjunto() != null) {
            try {
                MultipartFile adjunto = form.getArchivoAdjunto();
                String originalFilename = adjunto.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String fileName = FileUtil.generateFileName(extension);
                String filePath = FileUtil.generateFilePath("preguntas", fileName);


                // Guardar el archivo en el servidor
                byte[] bytes = adjunto.getBytes();
                FileCopyUtils.copy(bytes, new File(filePath));
                quizPregunta.setImagen(filePath);
                
                 
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(quizPregunta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute QuizPreguntaForm form,@PathVariable Long id){
        Optional<QuizPregunta> existingQuizPregunta = service.findById(id);

        if (!existingQuizPregunta.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        QuizPregunta quizPregunta = existingQuizPregunta.get();
        quizPregunta.setPregunta1(form.getPregunta1());
        quizPregunta.setPregunta2(form.getPregunta2());
        quizPregunta.setPregunta3(form.getPregunta3());
        quizPregunta.setPregunta4(form.getPregunta4());
        quizPregunta.setQuiz(form.getQuiz());
        quizPregunta.setRta(form.getRta());


        if (form.getArchivoAdjunto() != null) {
            try {
                
                MultipartFile adjunto = form.getArchivoAdjunto();
                String originalFilename = adjunto.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String fileName = FileUtil.generateFileName(extension);
                String filePath = FileUtil.generateFilePath("preguntas", fileName);

                // Guardar el archivo en el servidor
                byte[] bytes = adjunto.getBytes();
                FileCopyUtils.copy(bytes, new File(filePath));

                // Eliminar la imagen anterior
                String imagenAnterior = quizPregunta.getImagen();
                if (imagenAnterior != null) {
                    FileUtil.deleteFile(imagenAnterior);
                }


                quizPregunta.setImagen(filePath);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
        Optional<QuizPregunta> o = service.update(quizPregunta,id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        Optional<QuizPregunta> o = service.findById(id);
        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.ok(1); // Devuelve 1 si se eliminó correctamente
        }
        return ResponseEntity.ok(0); // Devuelve 0 si no se pudo eliminar
    }
    
}
