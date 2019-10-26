package com.cmd.hit.main.service.emptyService

import androidx.fragment.app.Fragment
import com.cmd.hit.main.model.entry.Params
import com.cmd.hit.main.service.IImService

class EmptyImService : IImService {
    override fun obtainMessageFragment(params: Params): Fragment {
        return Fragment()
    }

}