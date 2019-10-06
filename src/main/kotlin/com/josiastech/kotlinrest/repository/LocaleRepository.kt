package com.josiastech.kotlinrest.repository

import com.josiastech.kotlinrest.entity.Locales
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface LocaleRepository : CrudRepository<Locales, String> {
    @Query("SELECT l FROM Locales l where l.codigo like ?1 and upper(l.nombre) like ?2")
    fun findLike(codigo: String, nombre: String):Iterable<Locales>
}