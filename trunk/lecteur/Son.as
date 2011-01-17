package {
	import flash.media.SoundChannel;
	import flash.media.Sound;
	import flash.media.SoundTransform;
	
	import flash.net.URLRequest;
	
	public class Son extends Media{
		
		private var playin:Boolean = true;
		private var son:Sound;
		private var channel:SoundChannel;
		private var transf:SoundTransform;
		
		public function Son(url:String, titre:String){
			this.titre = titre;
			var urlRequest:URLRequest = new URLRequest(url);
			this.son = new Sound();
			this.son.load(urlRequest);
			
			this.transf = new SoundTransform();
		}
		public function play(){
			this.channel = this.son.play(this.pos);
			this.channel.soundTransform = transf;
		}
		public function pause(){
			this.pos = this.channel.position;
			this.channel.stop();
		}
		public function stop(){
			this.pos = 0;
			this.channel.stop();
		}
		public function setVolume(vol:int):void{
			var x:Number = vol/100;
			this.transf.volume = x;
			this.channel.soundTransform = transf;
		}
	}
}