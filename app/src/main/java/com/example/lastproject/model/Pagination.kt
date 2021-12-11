package com.example.lastproject.model

class Pagination (var page : Int, var total : Int, var pages :Int){

    val totalPages :Int = pages/20
}