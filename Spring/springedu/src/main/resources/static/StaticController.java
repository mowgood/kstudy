@Controller
public class StaticController {	
	public StaticController() {
		System.out.println("HelloController Create object");
	}
	@RequestMapping("/static")
	public ModelAndView xxx(){
		InternalResourceView view = new InternalResourceView("/staticview.html");
		ModelAndView viewModel = new ModelAndView(view);
		return viewModel;
	}	
}