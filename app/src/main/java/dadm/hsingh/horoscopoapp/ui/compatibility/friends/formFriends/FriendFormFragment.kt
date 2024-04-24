package dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FormsFriendBinding

class FriendFormFragment : DialogFragment(R.layout.forms_friend){

    private var _binding: FormsFriendBinding? = null
    private  val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FormsFriendBinding.bind(view)

        binding.buttonCancel.setOnClickListener {
            this.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        // Cambiar el tamaño del diálogo
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,  // Ancho deseado, por ejemplo: ViewGroup.LayoutParams.MATCH_PARENT para que ocupe todo el ancho
            ViewGroup.LayoutParams.WRAP_CONTENT   // Altura deseada, por ejemplo: ViewGroup.LayoutParams.WRAP_CONTENT para ajustarse al contenido
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}