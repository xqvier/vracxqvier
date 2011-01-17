package{
	import flash.media.Video;
	
	public class Vid extends Media {
		private var playin:Boolean;
		private var video:Video;
		
		public function Vid(url:String, titre:String):void{
			super(titre);
			var urlRequest:URLRequest = new URLRequest(url);
			var netConnect:NetConnection = new NetConnection();
			netConnect.connect(url);
			var netStream:NetStream = new NetStream(netConnect);
			video = new Video();
			video.attachNetStream(netStream);
			
			
			
		}
	}
	
}