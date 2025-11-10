package com.example.applicationdecontact


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {

    // 1. DÉCLARATION de toutes les vues
    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress1: EditText
    private lateinit var etAddress2: EditText
    private lateinit var rgCategory: RadioGroup
    private lateinit var btnAdd: Button
    private lateinit var btnReset: Button
    private lateinit var tvContactDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // 2. BINDING de toutes les vues
        etFullName = findViewById(R.id.etFullName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress1 = findViewById(R.id.etAddress1)
        etAddress2 = findViewById(R.id.etAddress2)
        rgCategory = findViewById(R.id.rgCategory)
        btnAdd = findViewById(R.id.btnAdd)
        btnReset = findViewById(R.id.btnReset)
        tvContactDisplay = findViewById(R.id.tvContactDisplay)

        // 3. DÉFINITION des événements des boutons
        btnAdd.setOnClickListener {
            ajouterContact()
        }

        btnReset.setOnClickListener {
            reinitialiser()
        }
    }

    // 4. FONCTION pour ajouter un contact
    private fun ajouterContact() {
        // Récupérer toutes les valeurs
        val nom = etFullName.text.toString().trim()
        val telephone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val adresse1 = etAddress1.text.toString().trim()
        val adresse2 = etAddress2.text.toString().trim()

        // Validation des champs obligatoires
        if (nom.isEmpty()) {
            Toast.makeText(this, "Le nom complet est obligatoire", Toast.LENGTH_SHORT).show()
            etFullName.requestFocus()
            return
        }

        if (telephone.isEmpty()) {
            Toast.makeText(this, "Le téléphone est obligatoire", Toast.LENGTH_SHORT).show()
            etPhone.requestFocus()
            return
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "L'email est obligatoire", Toast.LENGTH_SHORT).show()
            etEmail.requestFocus()
            return
        }

        if (adresse1.isEmpty()) {
            Toast.makeText(this, "L'adresse (ligne 1) est obligatoire", Toast.LENGTH_SHORT).show()
            etAddress1.requestFocus()
            return
        }

        // Récupérer la catégorie sélectionnée
        val selectedId = rgCategory.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une catégorie", Toast.LENGTH_SHORT).show()
            return
        }

        val radioButton = findViewById<RadioButton>(selectedId)
        val category = radioButton.text.toString()

        // Créer un texte formaté avec toutes les infos
        val contactInfo = """
            📋 Contact ajouté:
            
            👤 Nom: $nom
            📞 Tél: $telephone
            📧 Email: $email
            🏠 Adresse: $adresse1${if (adresse2.isNotEmpty()) ", $adresse2" else ""}
            📂 Catégorie: $category
        """.trimIndent()

        // Afficher dans tvContactDisplay
        tvContactDisplay.text = contactInfo

        // Afficher un Toast de confirmation
        Toast.makeText(this, "Contact ajouté avec succès!", Toast.LENGTH_LONG).show()
    }

    // 5. FONCTION pour réinitialiser le formulaire
    private fun reinitialiser() {
        // Vider tous les EditText
        etFullName.setText("")
        etPhone.setText("")
        etEmail.setText("")
        etAddress1.setText("")
        etAddress2.setText("")

        // Décocher les RadioButtons
        rgCategory.clearCheck()

        // Réinitialiser l'affichage
        tvContactDisplay.text = "Aucun contact ajouté"

        // Remettre le focus sur le premier champ
        etFullName.requestFocus()

        // Afficher un Toast
        Toast.makeText(this, "Formulaire réinitialisé", Toast.LENGTH_SHORT).show()
    }
}