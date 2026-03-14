package entity;

import java.util.Date;

public class Livre {
    private String isbn;
		private String titre;
		private String auteur;
		private Date datePublication;

        public Livre(String isbn, String titre, String auteur, Date datePublication) {
            this.isbn = isbn;
            this.titre = titre;
            this.auteur = auteur;
            this.datePublication = datePublication;
        }

		public Livre() {
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitre() {
			return titre;
		}

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public String getAuteur() {
			return auteur;
		}

		public void setAuteur(String auteur) {
			this.auteur = auteur;
		}

		public Date getDatePublication() {
			return datePublication;
		}

		public void setDatePublication(Date datePublication) {
			this.datePublication = datePublication;
		}
}
