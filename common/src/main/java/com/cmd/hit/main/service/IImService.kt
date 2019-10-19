package com.cmd.hit.main.service

import androidx.fragment.app.Fragment
import com.cmd.hit.main.model.entry.Params

interface IImService : IService {

    fun obtainMessageFragment(params: Params) : Fragment

}