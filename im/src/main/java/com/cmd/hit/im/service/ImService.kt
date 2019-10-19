package com.cmd.hit.im.service

import androidx.fragment.app.Fragment
import com.cmd.hit.im.enity.ImParams
import com.cmd.hit.im.ui.fragment.ChatMessageFragment
import com.cmd.hit.main.model.entry.Params
import com.cmd.hit.main.service.IImService

class ImService : IImService {
    override fun obtainMessageFragment(params: Params): Fragment {
        if (params !is ImParams) {
            return Fragment()
        }
        val imParams : ImParams = params
        return ChatMessageFragment.newInstance(imParams.count)
    }
}