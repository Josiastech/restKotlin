package com.josiastech.kotlinrest.entity


import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "locales")
data class Locales  (@Id @Basic(optional = false)
    @NotNull
    @Size(min=1, max = 5)
    @JsonProperty(value = "codigo")
    @Column(name = "loc_codi") val codigo : String,
            @JsonProperty(value = "nombre")
            @Column(name = "nombre") val nombre : String
)
{

}