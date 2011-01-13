package {
	import flash.media.SoundChannel;
	import flash.media.Sound;
	import flash.media.SoundTransform;
	
	import flash.net.URLRequest;
	
	public class Son {
		private var titre:String;
		private var pos:Number = 0;
		
		private var playin:Boolean = true;
		private var son:Sound;
		private var channel:SoundChannel;
		private var transf:SoundTransform;
		
		public function Son(url:String){
			var urlRequest:URLRequest = new URLRequest(url);
			this.son = new Sound();
			this.son.load(urlRequest);
			
			this.transf = new SoundTransform();
		}
		public function play(){
			this.channel = son.play(pos);
			this.channel.soundTransform = transf;
		}
		public function pause(){
			this.pos = channel.position;
			this.channel.stop();
		}
		public function stop(){
			this.pos = 0;
			this.channel.stop();
		}
	}
}