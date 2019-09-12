package
{
	import flash.display.MovieClip;
	
	import fl.transitions.Tween;
	import fl.transitions.easing.None;

	public class MyTween
	{
		private var _twn:Tween;
		private var tweenArray:Array = ["Blinds","Fade","Iris","PixelDissolve","Fly","Rotate","Squeeze","Wipe","Zoom","Photo"];
		public function MyTween(content:MovieClip,templet:int,index:int)
		{
			var tweenStr:String = tweenArray[templet-1];
			switch(tweenStr){
				case "Blinds":
				case "Rotate":
					switch(index){
						case 0:
							_twn = new Tween(content, "y", None.easeInOut, 0, -15, 1.4, true);
							break;
						case 1:
						case 3:
							_twn = new Tween(content, "x", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 2:
						case 4:
							_twn = new Tween(content, "y", None.easeInOut, 0, 15, 1.4, true);
							break;
						default:
							_twn = new Tween(content, "x", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
				case "Fade":
					switch(index){
						case 1:
						case 3:
							_twn = new Tween(content, "x", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 0:
						case 2:
						case 4:
						default:
							_twn = new Tween(content, "x", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
				case "Iris":
					switch(index){
						case 1:
						case 3:
							_twn = new Tween(content, "y", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 0:
						case 2:
						case 4:
						default:
							_twn = new Tween(content, "y", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
				case "PixelDissolve":
					switch(index){
						case 1:
							_twn = new Tween(content, "y", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 3:
							_twn = new Tween(content, "x", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 0:
						case 4:
							_twn = new Tween(content, "y", None.easeInOut, 0, -15, 1.4, true);
							break;
						case 2:
						default:
							_twn = new Tween(content, "x", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
				case "Fly":
				case "Wipe":
					switch(index){
						case 0:
						case 4:
							_twn = new Tween(content, "y", None.easeInOut, 0, -15, 1.4, true);
							break;
						case 1:
							_twn = new Tween(content, "x", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 2:
							_twn = new Tween(content, "y", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 3:
						default:
							_twn = new Tween(content, "x", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
				default:
					switch(index){
						case 0:
						case 4:
							_twn = new Tween(content, "y", None.easeInOut, 0, -15, 1.4, true);
							break;
						case 1:
							_twn = new Tween(content, "x", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 2:
							_twn = new Tween(content, "y", None.easeInOut, 0, 15, 1.4, true);
							break;
						case 3:
						default:
							_twn = new Tween(content, "x", None.easeInOut, 0, -15, 1.4, true);
							break;
					}
					break;
			}
		}
		public function get tween():Tween 
		{
			return this._twn;
		}
	}
}