package {
	public class Playlist {
		private var list:Array = [];
		private var position:int = 0;
		
		
		public function Playlist(){
			
		}
		public function add(son:Son){
			list.push(son);
		}
		public function play(){
			list[position].play();
		}
		public function del(son:Son){
		}
		public function pause(){
			list[position].pause();
		}
		public function stop(){
			list[position].stop();
		}
	}
}