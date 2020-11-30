//package demarrageordi.rest;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.validation.BindingResult;
//
///**
// * Custom Response.
// * 
// * @param <T> : an entity.
// */
//public class CustomResponse<T> {
//
//	/** Data result container **/
//	private T data;
//	/** Business errors list **/
//	private List<String> errors;
//
//	public CustomResponse() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public T getData() {
//		return this.data;
//	}
//
//	public void setData(Object object) {
//		if (object instanceof File) {
//			setDataFile((File) object);
//		} else {
//			setDataT((T) object);
//		}
//	}
//
//	public void setDataT(final T data) {
//		this.data = data;
//	}
//
//	public void setDataFile(File createBatch) {
//		this.data = (T) createBatch;
//		System.out.println(this.data);
//	}
//
//	public List<String> getErrors() {
//		if (this.errors == null) {
//			this.errors = new ArrayList<String>();
//		}
//		return this.errors;
//	}
//
//	public void setErrors(final List<String> errors) {
//		this.errors = errors;
//	}
//
//	/**
//	 * Valorizing errors parameter with results of Bean Validators
//	 * 
//	 * @param result: BindingResult object
//	 */
//	public void valorizeErrorsList(final BindingResult result) {
//		List<String> errors = new ArrayList<String>();
//		result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
//		this.setErrors(errors);
//	}
//
//	public void setError(Exception e) {
//		List<String> errors = new ArrayList<String>();
//		errors.add(e.getMessage());
//		this.setErrors(errors);
//	}
//
//}
