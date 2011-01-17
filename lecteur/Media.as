package  {
	
	public class Media {
		protected var titre:String;
		protected var pos:Number = 0;

		public function Media(titre:String) {
			this.titre = titre;
		}
		public function getTitre():String{
			return this.titre;
		}

	}
	
}
