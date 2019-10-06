package com.josiastech.kotlinrest.controller

import com.josiastech.kotlinrest.entity.Locales
import com.josiastech.kotlinrest.repository.LocaleRepository
import com.josiastech.kotlinrest.exceptions.ConflictException
import com.josiastech.kotlinrest.exceptions.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/")
class ApiController {

    @Autowired
    lateinit var localeRepository: LocaleRepository


    @CrossOrigin("http://localhost:4200")
    @GetMapping("/")
    fun getAll(): Iterable<Locales>
    {
        return localeRepository.findAll();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{codigo}/{nombre}")
    fun getByQuery(@PathVariable codigo:String, @PathVariable nombre:String): Iterable<Any>
    {
        return localeRepository.findLike("%"+codigo+"%","%"+nombre.toUpperCase()+"%");
//		return localeRepository.findByCodigo("%"+codigo+"%");//,"%"+nombre.toUpperCase()+"%");
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{codigo}")
    fun getByCodigo(@PathVariable codigo:String):Locales
    {
        return localeRepository.findById(codigo).orElseThrow({  ->  NotFoundException(codigo) });
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/")
    fun insertar(@RequestBody locales:Locales): ResponseEntity<Any>
    {
        if (localeRepository.existsById(locales.codigo ) )
            return ResponseEntity(HttpStatus.CONFLICT);

        localeRepository.save(locales)

        return ResponseEntity
                .created( URI("/api/"+locales.codigo)).body("");
    }
    @CrossOrigin("http://localhost:4200")
    @DeleteMapping ("/{codigo}")
    fun deleteByCodigo(@PathVariable codigo:String):ResponseEntity<Any>
    {
        if (!localeRepository.existsById(codigo) )
            throw NotFoundException(codigo)
        localeRepository.deleteById(codigo);
        return ResponseEntity( HttpStatus.OK)
    }

    @CrossOrigin("http://localhost:4200")
    @PutMapping ("/{codigo}")
    fun update(@PathVariable codigo:String,@RequestBody locales:Locales):ResponseEntity<Any>
    {
        if (!localeRepository.existsById(codigo) )
            throw NotFoundException(codigo)

        if (!codigo.equals(locales.codigo))
            throw ConflictException(codigo)
        localeRepository.save(locales);
        return ResponseEntity( HttpStatus.OK)
    }

}