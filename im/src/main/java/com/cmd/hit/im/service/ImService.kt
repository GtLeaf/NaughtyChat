package com.cmd.hit.im.service

import androidx.fragment.app.Fragment
import com.cmd.hit.im.ui.fragment.ChatMessageFragment
import com.cmd.hit.main.service.IImService

class ImService : IImService {
    override fun obtainMessageFragment(): Fragment {
        return ChatMessageFragment.newInstance(3)
    }
}