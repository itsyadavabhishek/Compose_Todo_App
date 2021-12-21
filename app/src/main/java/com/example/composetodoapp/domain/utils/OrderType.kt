package com.example.composetodoapp.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
