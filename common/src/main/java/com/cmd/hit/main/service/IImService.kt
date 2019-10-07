package com.cmd.hit.main.service

import androidx.fragment.app.Fragment

interface IImService : IService {

    fun obtainMessageFragment() : Fragment

}