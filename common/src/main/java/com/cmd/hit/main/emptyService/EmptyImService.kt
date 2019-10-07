package com.cmd.hit.main.emptyService

import androidx.fragment.app.Fragment
import com.cmd.hit.main.service.IImService

class EmptyImService : IImService {
    override fun obtainMessageFragment(): Fragment {
        return Fragment()
    }
}