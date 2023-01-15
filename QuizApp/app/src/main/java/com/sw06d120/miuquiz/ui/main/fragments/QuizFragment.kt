package com.sw06d120.miuquiz.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.sw06d120.miuquiz.R
import com.sw06d120.miuquiz.models.QuizViewModel
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.fragment_quiz.view.*

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        view.backHome.setOnClickListener {
            viewModel.reset()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }
        view.next.setOnClickListener {
            if (viewModel.currentIndex.value!! < viewModel.questions.size - 1) {
                viewModel.goNext()
                Navigation.findNavController(view).navigate(R.id.quizPrevFragment)
            } else {
                Navigation.findNavController(view).navigate(R.id.resultFragment)
            }
        }
        print("entered new quiz!")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.currentIndex.observe(this, Observer { currentQuestionId ->
            txtQuestionId.text = viewModel.getCurrentQuestion().question.question
            txtAnswers.text = viewModel.getCurrentQuestion().choices.toString()
        })
    }

}