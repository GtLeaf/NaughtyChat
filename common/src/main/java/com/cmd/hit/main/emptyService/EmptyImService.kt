package com.cmd.hit.main.emptyService

import androidx.fragment.app.Fragment
import com.cmd.hit.main.entry.Params
import com.cmd.hit.main.service.IImService

class EmptyImService : IImService {
    override fun obtainMessageFragment(params: Params): Fragment {
        return Fragment()
    }

}