package {
	import flash.net.URLRequest;
	import com.adobe.serialization.json.JSON;
	import flash.net.URLLoader;

	public class Playlist {
		private var list:Array = [];
		private var position:int = 0;
		
		
		public function Playlist(){
			load();
		}
		
		private function load():void{
			var urlreq:URLRequest = new URLRequest("playlist.json");
			var urlload:URLLoader = new URLLoader(urlreq)
			var json:JSON = new JSON();
			//var o:Object = JSON.decode(urlload) as String;
			//var s:String = o as String;
			//trace(s);
			
			var son:Son = new Son("sounds/possible_future.mp3");
			add(son);
			
		}
		public function add(med:Media):void{
			list.push(med);
		}
		public function play():void{
			list[position].play();
		}
		public function del(son:Son):void{
		}
		public function pause():void{
			list[position].pause();
		}
		public function stop():void{
			list[position].stop();
		}
		public function setVolume(vol:int):void{
			list[position].setVolume(vol);
		}
	}
}