package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.ProjectDAO;
import com.ui.dao.PropertyDAO;
import com.ui.model.Project;
import com.ui.model.ProjectAmenity;
import com.ui.model.ProjectArea;
import com.ui.model.ProjectBank;
import com.ui.model.ProjectDetail;
import com.ui.model.ProjectFloorLayout;
import com.ui.model.ProjectPaymentSchedule;
import com.ui.model.ProjectPriceInfo;
import com.ui.model.ProjectSpecification;
import com.ui.model.Slider;
import com.ui.model.UnitMaster;

@RestController
public class ProjectController {
	@Autowired
	ProjectDAO projectDAO;
	@Autowired
	PropertyDAO propertyDAO;

	Project project;
	ProjectSpecification projectSpecification;
	ProjectAmenity projectAmenity;
	ProjectDetail projectDetail;
	ProjectPriceInfo projectPriceInfo;
	ProjectPaymentSchedule projectPaymentSchedule;
	ProjectArea projectArea;
	UnitMaster unitMaster;
	ProjectFloorLayout projectFloorLayout;

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@RequestMapping(value = "/getProjects", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(HttpServletRequest request) {
		logger.info("***** GET PROJECTS *****");
		List<Project> t = projectDAO.getAllProjects();
		return t;
	}

	@RequestMapping(value = "/getProjectsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjectsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET PROJECT BY PAGE *****");
		List<Project> t = projectDAO.getAllProjectsByPage(pagesize, startindex);
		return t;
	}

	@RequestMapping(value = "/searchProject", method = RequestMethod.GET, produces = "application/json")
	public List<Project> searchProject(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH PROJECT *****");
		List<Project> t = projectDAO.searchProject(keyword);
		return t;
	}

	@RequestMapping(value = "addProject", method = RequestMethod.POST)
	public String addProject(@RequestParam(value = "projectlogo", required = false) MultipartFile projectlogo,
			@RequestParam(value = "projectlayout", required = false) MultipartFile projectlayout,
			@RequestParam(value = "projectmainimage", required = false) MultipartFile projectmainimage,
			@RequestParam(value = "projectpdf", required = false) MultipartFile projectpdf, String projecttitle,
			String projectsubtitle, String projectcode, int locationid, int architectid, int structuraldesignerid,
			int legaladvisorid, int developercompanyid, int propertytypeid, String rereapproved, String rerano,
			String drainage, String watersource, String workstatus, String description, int valuex, int valuey, int valuew, int valueh, String projectsitelink, int sequence,String shown_project, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD PROJECT *****");
		String result = null;
		String logo = null;
		String layout = null;
		String projmainimg = null;
		String projctpdf = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		if (projectlogo != null) {
			try {
				byte[] bytes = projectlogo.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectLogo");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectLogo/");
				File uploadfile = new File(path + File.separator + projectlogo.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				
				logo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectLogo/" + projectlogo.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String color1 = "#ffffff";
		if (projectlayout != null) {
		  try
          {  
                  Random random = new Random();
                  int randomnumber = 100000 + random.nextInt(900000);
                  
                  byte[] bytes =  projectlayout.getBytes();
                  
                  File dir = new File(request.getRealPath("")+"/resources/front/images/" + File.separator + "ProjectLayout");
                  if (!dir.exists()) 
                      dir.mkdirs();
                  
                  String path = request.getRealPath("/resources/front/images/ProjectLayout/");
                  File uploadfile = new File(path+File.separator+randomnumber+projectlayout.getOriginalFilename());
                  
                  /********* Today Start **********/
                  
                  int height=430, width=1000;
                  
                  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                  try
                  {
                      BufferedImage img = ImageIO.read(in);
                      
                      int original_width = img.getWidth();
                      int original_height = img.getHeight();
                      int bound_width = 1000;
                      int bound_height = 430;
                      int new_width = original_width;
                      int new_height = original_height;
                      
                      if (original_height/bound_height > original_width/bound_width) {
                          bound_width = (int) (bound_height * original_width / original_height);
                      } else {
                          bound_height = (int) (bound_width * original_height / original_width);
                      }
                      
                      Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
                      
                      
                      BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                      
                      Graphics2D drawer = imageBuff.createGraphics() ;
                      Color c = Color.decode(color1);
                      drawer.setBackground(c);
                      drawer.clearRect(0,0,width,height);
                      
                      imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
                      
                      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                      ImageIO.write(imageBuff, "jpg", buffer);

                      bytes = buffer.toByteArray();
                  }
                  catch (IOException e)
                  {
                  }                   
                  /********* Today End **********/
                  
                  System.out.println("*********************Path"+path);         
                  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                  bufferedOutputStream.write(bytes);
                  bufferedOutputStream.close();
                                          
              layout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/everest/resources/front/images/ProjectLayout/"+randomnumber+projectlayout.getOriginalFilename();
               
             
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
			/*try {
				byte[] bytes = projectlayout.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectLayout");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectLayout/");
				File uploadfile = new File(path + File.separator + projectlayout.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				layout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectLayout/" + projectlayout.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}*/
		}
		if (projectmainimage != null) {
    		if(projectmainimage.getOriginalFilename() != "")
            {
                try
                {           
                    byte[] bytes =  projectmainimage.getBytes();
                    
                    File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "ProjectImage");
                    if (!dir.exists()) 
                        dir.mkdirs();
                    String path = request.getRealPath("/resources/admin/images/ProjectImage/");
                    File uploadfile = new File(path+File.separator+projectmainimage.getOriginalFilename());
                    
                    
                    
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    try
                    {
                        BufferedImage img = ImageIO.read(in);
                        
                        Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
                        BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
                        Graphics2D drawer = SubImgage.createGraphics();
                        drawer.setComposite(AlphaComposite.Src);
                        drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        drawer.drawImage(scaledImage, valuew, valueh, null);
                        drawer.dispose();
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        ImageIO.write(SubImgage, "png", buffer);
                        bytes = buffer.toByteArray();
                    }
                    catch (IOException e)
                    {
                        //throw new ApplicationException("IOException in scale");
                    }
                   
                    
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
    
                    //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/jamnadasghariwalanew/resources/admin/images/slider/" + image.getOriginalFilename();
                    projmainimg = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/ProjectImage/" + projectmainimage.getOriginalFilename();
                }catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
		}
		
		if (projectpdf != null) {
          try {
              byte[] bytes = projectpdf.getBytes();
              File dir = new File(request.getSession().getServletContext().getRealPath("")
                      + "/resources/admin/images/" + File.separator + "ProjectPDF");
              if (!dir.exists())
                  dir.mkdirs();
              String path = request.getSession().getServletContext()
                      .getRealPath("/resources/admin/images/ProjectPDF/");
              File uploadfile = new File(path + File.separator + projectpdf.getOriginalFilename());
              BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
              bufferedOutputStream.write(bytes);
              bufferedOutputStream.close();
              File f = new File(path);
              File files[] = f.listFiles();

              for (int j = 0; j < files.length; j++) {
                  if (files[j].isFile()) {
                      System.out.println("File " + files[j].getName() + " " + files[j].length());
                  }
              }
              projctpdf = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                      + "/everest/resources/admin/images/ProjectPDF/" + projectpdf.getOriginalFilename();
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }
		try {
		project = new Project(projecttitle, projectsubtitle, projectcode, logo, locationid, architectid,
				structuraldesignerid, legaladvisorid, developercompanyid, propertytypeid, rereapproved, rerano, layout,
				drainage, watersource, workstatus, URLDecoder.decode(description, "UTF-8"), s, id, IpAddress, projmainimg, projctpdf, projectsitelink, sequence,shown_project);
		result = projectDAO.addProject(project);
    	 } catch (UnsupportedEncodingException e) {
           e.getMessage();
         }
		return result;
	}
	
	@RequestMapping(value = "editProject", method = RequestMethod.POST)
	public String editProject(@RequestParam(value = "projectlogoedit", required = false) MultipartFile projectlogoedit,
			@RequestParam(value = "projectlayoutedit", required = false) MultipartFile projectlayoutedit,
			@RequestParam(value = "projectmainimageedit", required = false) MultipartFile projectmainimageedit,
			@RequestParam(value = "projectpdf", required = false) MultipartFile projectpdf, int projectid,
			String projecttitle, String projectsubtitle, String projectcode, int locationid, int architectid,
			int structuraldesignerid, int legaladvisorid, int developercompanyid, int propertytypeid,
			String rereapproved, String rerano, String watersource, String drainage, String workstatus,
			String description, String projectlogo, String projectlayout,String projmainimg,int valuex, int valuey, int valuew, int valueh, String sitelink, String pdfpath, int sequence,String shown_project, HttpServletRequest request,
			HttpSession session) {
	logger.info("***** EDIT PROJECT *****");
		String result = null;
		String logo = projectlogo;
		String layout = projectlayout;
		String projmainimgs = projmainimg;
		String projecpdf = pdfpath;
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		if (projectlogoedit != null) {
			try {
				byte[] bytes = projectlogoedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectLogo");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectLogo/");
				File uploadfile = new File(path + File.separator + projectlogoedit.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				logo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectLogo/" + projectlogoedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String color1 = "#ffffff";
		if (projectlayoutedit != null) {
		  try
          {  
                  Random random = new Random();
                  int randomnumber = 100000 + random.nextInt(900000);
                  
                  byte[] bytes =  projectlayoutedit.getBytes();
                  
                  File dir = new File(request.getRealPath("")+"/resources/front/images/" + File.separator + "ProjectLayout");
                  if (!dir.exists()) 
                      dir.mkdirs();
                  
                  String path = request.getRealPath("/resources/front/images/ProjectLayout/");
                  File uploadfile = new File(path+File.separator+randomnumber+projectlayoutedit.getOriginalFilename());
                  
                  /********* Today Start **********/
                  
                  int height=430, width=1000;
                  
                  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                  try
                  {
                      BufferedImage img = ImageIO.read(in);
                      
                      int original_width = img.getWidth();
                      int original_height = img.getHeight();
                      int bound_width = 1000;
                      int bound_height = 430;
                      int new_width = original_width;
                      int new_height = original_height;
                      
                      if (original_height/bound_height > original_width/bound_width) {
                          bound_width = (int) (bound_height * original_width / original_height);
                      } else {
                          bound_height = (int) (bound_width * original_height / original_width);
                      }
                      
                      Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
                      
                      
                      BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                      
                      Graphics2D drawer = imageBuff.createGraphics() ;
                      Color c = Color.decode(color1);
                      drawer.setBackground(c);
                      drawer.clearRect(0,0,width,height);
                      
                      imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
                      
                      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                      ImageIO.write(imageBuff, "jpg", buffer);

                      bytes = buffer.toByteArray();
                  }
                  catch (IOException e)
                  {
                  }                   
                  /********* Today End **********/
                  
                  System.out.println("*********************Path"+path);         
                  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                  bufferedOutputStream.write(bytes);
                  bufferedOutputStream.close();
              
              
              //image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/alkapurihaveli/resources/front/images/DailyDarshan/"+randomnumber+darshanImage[i].getOriginalFilename();
              layout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/everest/resources/front/images/ProjectLayout/"+randomnumber+projectlayoutedit.getOriginalFilename();
               
             
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
			/*try {
				byte[] bytes = projectlayoutedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectLayout");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectLayout/");
				File uploadfile = new File(path + File.separator + projectlayoutedit.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				layout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectLayout/" + projectlayoutedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}*/
		}
		
		if (projectmainimageedit != null) {
			try {
				
				byte[] bytes = projectmainimageedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectImage");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectImage/");
				File uploadfile = new File(path + File.separator + projectmainimageedit.getOriginalFilename());
				ByteArrayInputStream in = new ByteArrayInputStream(bytes);
	        	try
	        	{
	        		BufferedImage img = ImageIO.read(in);
	        		
	        		Image scaledImage = img.getScaledInstance(valuew-1, valueh, Image.SCALE_SMOOTH);
	                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew-1, valueh);
	                Graphics2D drawer = SubImgage.createGraphics();
	                drawer.setComposite(AlphaComposite.Src);
	                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                drawer.drawImage(scaledImage, valuew-1, valueh, null);
	                drawer.dispose();
	                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	                ImageIO.write(SubImgage, "jpg", buffer);
	                bytes = buffer.toByteArray();
	        	}
	        	catch (IOException e)
	        	{
	        		//throw new ApplicationException("IOException in scale");
	        	}
				
				
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				
				projmainimgs = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectImage/" + projectmainimageedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (projectpdf != null) {
          try {
              byte[] bytes = projectpdf.getBytes();
              File dir = new File(request.getSession().getServletContext().getRealPath("")
                      + "/resources/admin/images/" + File.separator + "ProjectPDF");
              if (!dir.exists())
                  dir.mkdirs();
              String path = request.getSession().getServletContext()
                      .getRealPath("/resources/admin/images/ProjectPDF/");
              File uploadfile = new File(path + File.separator + projectpdf.getOriginalFilename());
              BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
              bufferedOutputStream.write(bytes);
              bufferedOutputStream.close();
              File f = new File(path);
              File files[] = f.listFiles();

              for (int j = 0; j < files.length; j++) {
                  if (files[j].isFile()) {
                      System.out.println("File " + files[j].getName() + " " + files[j].length());
                  }
              }
              projecpdf = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                      + "/everest/resources/admin/images/ProjectPDF/" + projectpdf.getOriginalFilename();
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }
		try {
		  project = new Project(projectid, projecttitle, projectsubtitle, projectcode, logo, locationid,
                architectid, structuraldesignerid, legaladvisorid, developercompanyid, propertytypeid, rereapproved,
                rerano, layout, watersource, drainage, workstatus, URLDecoder.decode(description, "UTF-8"), id, IpAddress, projmainimgs, projecpdf, sitelink, sequence,shown_project);
    
		  result = projectDAO.editProject(project);
	      } catch (UnsupportedEncodingException e) {
	         e.getMessage();
	      }
		return result;
	}
	
	@RequestMapping(value = "deleteProject", method = RequestMethod.DELETE)
	public void delete(int projectid) {
		logger.info("***** DELETE PROJECT *****");
		projectDAO.deleteProject(projectid);
		propertyDAO.updatePropertyStatus(projectid);		
	}

	@RequestMapping(value = "deleteProjectDetail", method = RequestMethod.DELETE)
	public void deletes(int projectdetailid) {
		logger.info("***** DELETE PROJECT DETAILS *****");
		projectDAO.deleteProjectDetail(projectdetailid);
	}

	@RequestMapping(value = "deleteProjectPriceDetail", method = RequestMethod.DELETE)
	public void deleteProject(int projectpriceinfoid) {
		logger.info("***** DELETE PROJECT PRICE DETAILS *****");
		projectDAO.deleteProjectPriceDetail(projectpriceinfoid);
	}

	@RequestMapping(value = "deleteProjectPaymentSchedule", method = RequestMethod.DELETE)
	public void deleteProjects(int id) {
		logger.info("***** DELETE PROJECT PAYMENT SCHEDULE *****");
		projectDAO.deleteProjectPaymentSchedule(id);
	}

	@RequestMapping(value = "/getProjectDetailById", method = RequestMethod.GET, produces = "application/json")
	public Project getProjectDetailById(int projectid, HttpServletRequest request) {
		logger.info("***** PROJECT DETAIL BY ID *****");
		Project p = projectDAO.getProjectDetailById(projectid);
		return p;
	}

	@RequestMapping(value = "addProjectArea", method = RequestMethod.POST)
	public String addProjectArea(int categoryid, int subcategoryid, int typeid, int unitmasterid, int areaid,
			int unitid, String areavalue, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT AREA *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		int projectid = projectDAO.getLastProjectId();
		projectArea = new ProjectArea(projectid, categoryid, subcategoryid, typeid, unitmasterid, areaid, unitid,
				areavalue, id, IpAddress);
		projectDAO.addProjectArea(projectArea);

		return "Success";
	}

	@RequestMapping(value = "editProjectArea", method = RequestMethod.POST)
	public String editProjectArea(int projectid, int categoryid, int subcategoryid, int typeid, int unitmasterid,
			int areaid, int unitid, String areavalue, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT AREA *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		projectArea = new ProjectArea(projectid, categoryid, subcategoryid, typeid, unitmasterid, areaid, unitid,
				areavalue, id, IpAddress);
		projectDAO.addProjectArea(projectArea);

		return "Success";
	}

	@RequestMapping(value = "addProjectDetails", method = RequestMethod.POST)
	public String addProjectDetails(int realestatetypeid, int realestatesubcategoryid, int unitmasterid,
			int realestateid, String numberofunit, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int projectid = projectDAO.getLastProjectId();
		projectDetail = new ProjectDetail(projectid, realestatetypeid, realestatesubcategoryid, unitmasterid,
				realestateid, numberofunit, s, id, IpAddress);
		projectDAO.addProjectDetails(projectDetail);

		return "Success";
	}
	
	
	@RequestMapping(value = "addProjectBank", method = RequestMethod.POST)
    public String addProjectBank(int bankid, String bankTitle, HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD PROJECT BANKS *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        
        int projectid = projectDAO.getLastProjectId();
        
        ProjectBank b = new ProjectBank();
        b.setBankId(bankid);
        b.setProjectId(projectid);
        b.setTitle(bankTitle);
        b.setCreatedBy(id);
        b.setIpAddress(IpAddress);
        
        projectDAO.addProjectBank(b);

        return "Success";
    }

	@RequestMapping(value = "addProjectPriceDetails", method = RequestMethod.POST)
	public String addProjectPriceDetails(int realestatetypeid, int realestatesubcategoryid, int realestateid,
			int unitmasterid, String pricelable, String pricevalue, int measurementunitid, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD PROJECT PRICE DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int projectid = projectDAO.getLastProjectId();
		projectPriceInfo = new ProjectPriceInfo(projectid, realestatetypeid, realestatesubcategoryid, realestateid,
				unitmasterid, pricelable, pricevalue, measurementunitid, s, id, IpAddress);
		projectDAO.addProjectPriceDetails(projectPriceInfo);

		return "Success";
	}

	@RequestMapping(value = "addProjectPaymentSchedule", method = RequestMethod.POST)
	public String addProjectPaymentSchedule(String sequence, String sequencetitle, String lable, float value,
			int measurementunitid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT PAYMENT SCHEDULE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int projectid = projectDAO.getLastProjectId();
		projectPaymentSchedule = new ProjectPaymentSchedule(projectid, sequence, sequencetitle, lable, value,
				measurementunitid, s, id, IpAddress);
		projectDAO.addProjectPaymentSchedule(projectPaymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "editProjectPaymentSchedule", method = RequestMethod.POST)
	public String editProjectPaymentSchedule(int projectid, String sequence, String sequencetitle, String lable,
			int value, int measurementunitid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PROJECT PAYMENT SCHEDULE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		projectPaymentSchedule = new ProjectPaymentSchedule(projectid, sequence, sequencetitle, lable, value,
				measurementunitid, s, id, IpAddress);
		projectDAO.addProjectPaymentSchedule(projectPaymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "editProjectPriceDetails", method = RequestMethod.POST)
	public String editProjectPriceDetails(int projectid, int categoryid, int subcategoryid, int realestateid,
			int unitmasterid, String pricelable, String pricevalue, int measurementunitid, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT PROJECT PRICE DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		projectPriceInfo = new ProjectPriceInfo(projectid, categoryid, subcategoryid, realestateid, unitmasterid,
				pricelable, pricevalue, measurementunitid, s, id, IpAddress);
		projectDAO.addProjectPriceDetails(projectPriceInfo);

		return "Success";
	}

	@RequestMapping(value = "editProjectDetails", method = RequestMethod.POST)
	public String editProjectDetails(int projectid, int categoryid, int subcategoryid, int towertitle, int realestateid,
			String numberofunit, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PROJECT DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		projectDetail = new ProjectDetail(projectid, categoryid, subcategoryid, towertitle, realestateid, numberofunit,
				s, id, IpAddress);
		projectDAO.addProjectDetails(projectDetail);

		return "Success";
	}

	@RequestMapping(value = "/addProjectSpecification", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addProjectSpecification(@RequestParam(value = "sfileadd", required = false) MultipartFile[] sfileadd,
			String title[], String subtitle[], String description[], HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT SPECIFICATION *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String filepath = null;
		if (sfileadd.length != 0) {
			try {
				for (int i = 0; i < sfileadd.length; i++) {
					if (sfileadd[i].getOriginalFilename() != null && sfileadd[i].getOriginalFilename() != "") {
						try {
							byte[] bytes = sfileadd[i].getBytes();
							File dir = new File(request.getSession().getServletContext().getRealPath("")
									+ "/resources/admin/images/" + File.separator + "ProjectSpecification");
							if (!dir.exists())
								dir.mkdirs();
							String path = request.getSession().getServletContext()
									.getRealPath("/resources/admin/images/ProjectSpecification/");
							File uploadfile = new File(path + File.separator + sfileadd[i].getOriginalFilename());
							BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
									new FileOutputStream(uploadfile));
							bufferedOutputStream.write(bytes);
							bufferedOutputStream.close();
							File f = new File(path);
							File files[] = f.listFiles();

							for (int j = 0; j < files.length; j++) {
								if (files[j].isFile()) {
									System.out.println("File " + files[j].getName() + " " + files[j].length());
								}
							}
							
							filepath = request.getScheme() + "://" + request.getServerName() + ":"
									+ request.getServerPort() + "/everest/resources/admin/images/ProjectSpecification/"
									+ sfileadd[i].getOriginalFilename();

							int projectid = projectDAO.getLastProjectId();
							projectSpecification = new ProjectSpecification(projectid, title[i + 1], subtitle[i + 1],
							      URLDecoder.decode(description[i + 1], "UTF-8"), filepath, id, IpAddress);
							projectDAO.addProjectSpecification(projectSpecification);

						} catch (Exception e) {
							e.printStackTrace();

							int projectid = projectDAO.getLastProjectId();
							projectSpecification = new ProjectSpecification(projectid, title[i + 1], subtitle[i + 1],
							      URLDecoder.decode(description[i + 1], "UTF-8"), filepath, id, IpAddress);
							projectDAO.addProjectSpecification(projectSpecification);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < description.length; i++) {
				int projectid = projectDAO.getLastProjectId();
				projectSpecification = new ProjectSpecification(projectid, title[i + 1], subtitle[i + 1],
						description[i + 1], filepath, id, IpAddress);
				projectDAO.addProjectSpecification(projectSpecification);
			}
		}
		return "Success";
	}

	@RequestMapping(value = "/addProjectAmenity", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addProjectAmenity(@RequestParam(value = "afileadd", required = false) MultipartFile[] afileadd,
			String title[], String subtitle[], String description[], HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROJECT AMENITY *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String filepath = null;
		if (afileadd.length != 0) {
			try {
				for (int i = 0; i < afileadd.length; i++) {
					if (afileadd[i].getOriginalFilename() != null && afileadd[i].getOriginalFilename() != "") {
						try {
							byte[] bytes = afileadd[i].getBytes();
							File dir = new File(request.getSession().getServletContext().getRealPath("")
									+ "/resources/admin/images/" + File.separator + "ProjectAmenity");
							if (!dir.exists())
								dir.mkdirs();
							String path = request.getSession().getServletContext()
									.getRealPath("/resources/admin/images/ProjectAmenity/");
							File uploadfile = new File(path + File.separator + afileadd[i].getOriginalFilename());
							BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
									new FileOutputStream(uploadfile));
							bufferedOutputStream.write(bytes);
							bufferedOutputStream.close();
							File f = new File(path);
							File files[] = f.listFiles();

							for (int j = 0; j < files.length; j++) {
								if (files[j].isFile()) {
									System.out.println("File " + files[j].getName() + " " + files[j].length());
								}
							}
							filepath = request.getScheme() + "://" + request.getServerName() + ":"
									+ request.getServerPort() + "/everest/resources/admin/images/ProjectAmenity/"
									+ afileadd[i].getOriginalFilename();

							int projectid = projectDAO.getLastProjectId();
							projectAmenity = new ProjectAmenity(projectid, title[i + 1], subtitle[i + 1],
							      URLDecoder.decode(description[i + 1], "UTF-8"), filepath, id, IpAddress);
							projectDAO.addProjectAmenity(projectAmenity);

						} catch (Exception e) {
							e.printStackTrace();

							int projectid = projectDAO.getLastProjectId();
							projectAmenity = new ProjectAmenity(projectid, title[i + 1], subtitle[i + 1],
							      URLDecoder.decode(description[i + 1], "UTF-8"), filepath, id, IpAddress);
							projectDAO.addProjectAmenity(projectAmenity);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i < description.length; i++) {
				int projectid = projectDAO.getLastProjectId();
				projectAmenity = new ProjectAmenity(projectid, title[i + 1], subtitle[i + 1], description[i + 1],
						filepath, id, IpAddress);
				projectDAO.addProjectAmenity(projectAmenity);
			}
		}
		return "Success";
	}
	
	
	@RequestMapping(value = "/addProjectFloorLayout", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addProjectFloorLayout(@RequestParam(value = "filelayout", required = false) MultipartFile[] filelayout,
            int categoryid[], int unitid[], String floorNumber[], String totalUnit[], HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD PROJECT Floor Layout *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String filepath = null;
        if (filelayout.length != 0) {
            try {
                for (int i = 0; i < filelayout.length; i++) {
                    if (filelayout[i].getOriginalFilename() != null && filelayout[i].getOriginalFilename() != "") {
                        try {
                            byte[] bytes = filelayout[i].getBytes();
                            File dir = new File(request.getSession().getServletContext().getRealPath("")
                                    + "/resources/admin/images/" + File.separator + "ProjectFloorLayout");
                            if (!dir.exists())
                                dir.mkdirs();
                            String path = request.getSession().getServletContext()
                                    .getRealPath("/resources/admin/images/ProjectFloorLayout/");
                            File uploadfile = new File(path + File.separator + filelayout[i].getOriginalFilename());
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                    new FileOutputStream(uploadfile));
                            bufferedOutputStream.write(bytes);
                            bufferedOutputStream.close();
                            File f = new File(path);
                            File files[] = f.listFiles();

                            for (int j = 0; j < files.length; j++) {
                                if (files[j].isFile()) {
                                    System.out.println("File " + files[j].getName() + " " + files[j].length());
                                }
                            }
                            filepath = request.getScheme() + "://" + request.getServerName() + ":"
                                    + request.getServerPort() + "/everest/resources/admin/images/ProjectFloorLayout/"
                                    + filelayout[i].getOriginalFilename();

                            int projectid = projectDAO.getLastProjectId();
                            
                            projectFloorLayout = new ProjectFloorLayout();
                            
                            projectFloorLayout.setProjectId(projectid);
                            projectFloorLayout.setCategoryId(categoryid[i]);
                            projectFloorLayout.setUnitId(unitid[i]);
                            projectFloorLayout.setFloorNumber(floorNumber[i]);
                            projectFloorLayout.setTotalUnit(totalUnit[i]);
                            projectFloorLayout.setImage(filepath);
                            projectFloorLayout.setCreatedBy(id);
                            projectFloorLayout.setIpAddress(IpAddress);
                           
                            projectDAO.addProjectFloorLayout(projectFloorLayout);

                        } catch (Exception e) {
                            e.printStackTrace();

                            int projectid = projectDAO.getLastProjectId();
                            projectFloorLayout = new ProjectFloorLayout();
                            
                            projectFloorLayout.setProjectId(projectid);
                            projectFloorLayout.setCategoryId(categoryid[i]);
                            projectFloorLayout.setUnitId(unitid[i]);
                            projectFloorLayout.setFloorNumber(floorNumber[i]);
                            projectFloorLayout.setTotalUnit(totalUnit[i]);
                            projectFloorLayout.setImage(filepath);
                            projectFloorLayout.setCreatedBy(id);
                            projectFloorLayout.setIpAddress(IpAddress);
                            projectDAO.addProjectFloorLayout(projectFloorLayout);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < categoryid.length; i++) {
                int projectid = projectDAO.getLastProjectId();
                projectFloorLayout = new ProjectFloorLayout();
                
                projectFloorLayout.setProjectId(projectid);
                projectFloorLayout.setCategoryId(categoryid[i]);
                projectFloorLayout.setUnitId(unitid[i]);
                projectFloorLayout.setFloorNumber(floorNumber[i]);
                projectFloorLayout.setTotalUnit(totalUnit[i]);
                projectFloorLayout.setImage(filepath);
                projectFloorLayout.setCreatedBy(id);
                projectFloorLayout.setIpAddress(IpAddress);
                projectDAO.addProjectFloorLayout(projectFloorLayout);
            }
        }
        return "Success";
    }
	
	
	@RequestMapping(value = "/editProjectFloorLayout", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editProjectFloorLayout(@RequestParam(value = "filelayout", required = false) MultipartFile filelayout,
            int projectid, int categoryid, int unitid, String floorNumber, String totalUnit, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT PROJECT Floor Layout *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String filepath = null;
                           
            if (filelayout.getOriginalFilename() != null && filelayout.getOriginalFilename() != "") {
                try {
                    byte[] bytes = filelayout.getBytes();
                    File dir = new File(request.getSession().getServletContext().getRealPath("")
                            + "/resources/admin/images/" + File.separator + "ProjectFloorLayout");
                    if (!dir.exists())
                        dir.mkdirs();
                    String path = request.getSession().getServletContext()
                            .getRealPath("/resources/admin/images/ProjectFloorLayout/");
                    File uploadfile = new File(path + File.separator + filelayout.getOriginalFilename());
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                            new FileOutputStream(uploadfile));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                    File f = new File(path);
                    File files[] = f.listFiles();

                    for (int j = 0; j < files.length; j++) {
                        if (files[j].isFile()) {
                            System.out.println("File " + files[j].getName() + " " + files[j].length());
                        }
                    }
                    filepath = request.getScheme() + "://" + request.getServerName() + ":"
                            + request.getServerPort() + "/everest/resources/admin/images/ProjectFloorLayout/"
                            + filelayout.getOriginalFilename();
                    
                    projectFloorLayout = new ProjectFloorLayout();
                    
                    projectFloorLayout.setProjectId(projectid);
                    projectFloorLayout.setCategoryId(categoryid);
                    projectFloorLayout.setUnitId(unitid);
                    projectFloorLayout.setFloorNumber(floorNumber);
                    projectFloorLayout.setTotalUnit(totalUnit);
                    projectFloorLayout.setImage(filepath);
                    projectFloorLayout.setCreatedBy(id);
                    projectFloorLayout.setIpAddress(IpAddress);
                   
                    projectDAO.addProjectFloorLayout(projectFloorLayout);
                    
                    return "Success";

                } catch (Exception e) {
                    e.printStackTrace();                    
                    projectFloorLayout = new ProjectFloorLayout();
                    
                    projectFloorLayout.setProjectId(projectid);
                    projectFloorLayout.setCategoryId(categoryid);
                    projectFloorLayout.setUnitId(unitid);
                    projectFloorLayout.setFloorNumber(floorNumber);
                    projectFloorLayout.setTotalUnit(totalUnit);
                    projectFloorLayout.setImage(filepath);
                    projectFloorLayout.setCreatedBy(id);
                    projectFloorLayout.setIpAddress(IpAddress);
                    projectDAO.addProjectFloorLayout(projectFloorLayout);
                    
                    return "Success";
                }
            }
        
        return "Success";
    }
	

	@RequestMapping(value = "/getProjectSpecificationById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectSpecification> getProjectSpecificationById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT SPECIFICATION BY ID *****");
		List<ProjectSpecification> ps = projectDAO.getProjectSpecificationById(projectid);
		return ps;
	}

	@RequestMapping(value = "/getProjectAmenityById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectAmenity> getProjectAmenityById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT AMENITY BY ID *****");
		List<ProjectAmenity> pa = projectDAO.getProjectAmenityById(projectid);
		return pa;
	}

	@RequestMapping(value = "/getProjectAreaById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectArea> getProjectAreaById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT DETAILS BY ID *****");
		List<ProjectArea> projectArea = projectDAO.getProjectAreaById(projectid);
		return projectArea;
	}

	@RequestMapping(value = "/getProjectDetailsById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectDetail> getProjectDetailsById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT DETAILS BY ID *****");
		List<ProjectDetail> projectDetail = projectDAO.getProjectDetailsById(projectid);
		return projectDetail;
	}

	@RequestMapping(value = "/getProjectPriceDetailsById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPriceInfo> getProjectPriceDetailsById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT DETAILS BY ID *****");
		List<ProjectPriceInfo> projectPriceInfo = projectDAO.getProjectPriceDetailsById(projectid);
		return projectPriceInfo;
	}

	@RequestMapping(value = "/getProjectPaymentScheduleById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPaymentSchedule> getProjectPaymentScheduleById(int projectid, HttpServletRequest request) {
		logger.info("***** GET PROJECT PAYMENT SCHEDULE BY ID *****");
		List<ProjectPaymentSchedule> projectPaymentSchedule = projectDAO.getProjectPaymentScheduleById(projectid);
		return projectPaymentSchedule;
	}

	@RequestMapping(value = "/getProjectFloorLayoutById", method = RequestMethod.GET, produces = "application/json")
    public List<ProjectFloorLayout> getProjectFloorLayoutById(int projectid, HttpServletRequest request) {
        logger.info("***** GET PROJECT FLOOR LAYOUT BY ID *****");
        List<ProjectFloorLayout> projectFloorLayout = projectDAO.getProjectFloorLayoutById(projectid);
        return projectFloorLayout;
    }
	
	@RequestMapping(value = "/getAllProjectBankById", method = RequestMethod.GET, produces = "application/json")
    public List<ProjectBank> getAllProjectBankById(int projectid, HttpServletRequest request) {
        logger.info("***** GET PROJECT BANKS BY ID *****");
        List<ProjectBank> b = projectDAO.getAllProjectBankById(projectid);
        return b;
    }
	
	@RequestMapping(value = "deleteSpecification", method = RequestMethod.DELETE)
	public void deleteSpecification(int projectspecificationid) {
		logger.info("***** DELETE SPECIFICATION *****");
		projectDAO.deleteSpecification(projectspecificationid);
	}

	@RequestMapping(value = "deleteAmenity", method = RequestMethod.DELETE)
	public void deleteAmenity(int projectamenityid) {
		logger.info("***** DELETE AMENITY *****");
		projectDAO.deleteAmenity(projectamenityid);
	}
	
	@RequestMapping(value = "deleteProjectFloorLayout", method = RequestMethod.DELETE)
    public void deleteProjectFloorLayout(int floorlayoutid) {
        logger.info("***** DELETE FLOOR LAYOUT *****");
        projectDAO.deleteProjectFloorLayout(floorlayoutid);
    }

	@RequestMapping(value = "editSpecificationRow", method = RequestMethod.POST)
	public String editSpecificationRow(@RequestParam(value = "sfileedit", required = false) MultipartFile sfileedit,
			int projectid, String title, String subtitle, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT SPECIFICATION *****");

		String filepath = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		if (sfileedit != null) {
			try {
				byte[] bytes = sfileedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectSpecification");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectSpecification/");
				File uploadfile = new File(path + File.separator + sfileedit.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				filepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectSpecification/" + sfileedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		try {
  		projectSpecification = new ProjectSpecification(projectid, title, subtitle, URLDecoder.decode(description, "UTF-8"), filepath, id, IpAddress);
  		projectDAO.addProjectSpecification(projectSpecification);
		} catch (UnsupportedEncodingException e) {
          e.getMessage();
        }
		return "Success";
	}

	@RequestMapping(value = "editAmenityRow", method = RequestMethod.POST)
	public String editAmenityRow(@RequestParam(value = "afileedit", required = false) MultipartFile afileedit,
			int projectid, String title, String subtitle, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT AMENITY *****");

		String filepath = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String des1 = description.replace("$", "&");
		String des2 = des1.replace("~", "#");
		String des3 = des2.replace("!", "%");

		if (afileedit != null) {
			try {
				byte[] bytes = afileedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "ProjectAmenity");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/ProjectAmenity/");
				File uploadfile = new File(path + File.separator + afileedit.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				filepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/ProjectAmenity/" + afileedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		projectAmenity = new ProjectAmenity(projectid, title, subtitle, des3, filepath, id, IpAddress);
		projectDAO.addProjectAmenity(projectAmenity);

		return "Success";
	}

	@RequestMapping(value = "/getProjectName", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjectName(HttpServletRequest request) {
		logger.info("***** GET PROPERTY NAME *****");
		List<Project> t = projectDAO.getProjectName();
		return t;
	}

	@RequestMapping(value = "deleteProjectAreaRow", method = RequestMethod.DELETE)
	public void deleteProjectAreaRow(int projectareaid) {
		logger.info("***** DELETE PROJECT AREA *****");
		projectDAO.deleteProjectAreaRow(projectareaid);
	}

	@RequestMapping(value = "/getProjectSubcategoryById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectDetail> getProjectSubcategoryById(int projectid, int categoryid, HttpServletRequest request) {
		logger.info("***** GET PROJECT SUBCATEGORY BY ID *****");
		List<ProjectDetail> projectDetail = projectDAO.getProjectSubcategoryById(projectid, categoryid);
		return projectDetail;
	}

	@RequestMapping(value = "/getProjectRealestateTypeById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectDetail> getProjectRealestateTypeById(int projectid, int categoryid, int subcategoryid,
			HttpServletRequest request) {
		logger.info("***** GET PROJECT REALESTATE TYPE BY ID *****");
		List<ProjectDetail> projectDetail = projectDAO.getProjectRealestateTypeById(projectid, categoryid,
				subcategoryid);
		return projectDetail;
	}

	@RequestMapping(value = "/getProjectUnitById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectDetail> getProjectUnitById(int projectid, int categoryid, int subcategoryid, int typeid,
			HttpServletRequest request) {
		logger.info("***** GET PROJECT REALESTATE TYPE BY ID *****");
		List<ProjectDetail> projectDetail = projectDAO.getProjectUnitById(projectid, categoryid, subcategoryid, typeid);
		return projectDetail;
	}

	@RequestMapping(value = "/getProjectUnitByProjectId", method = RequestMethod.GET, produces = "application/json")
    public List<ProjectDetail> getProjectUnitByProjectId(int projectid, HttpServletRequest request) {
        logger.info("***** GET PROJECT UNIT TYPE BY ID *****");
        List<ProjectDetail> projectDetail = projectDAO.getProjectUnitByProjectId(projectid);
        return projectDetail;
    }
	
	@RequestMapping(value = "/getProjectAreaForPropertyById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectArea> getProjectAreaForPropertyById(int projectid, int categoryid, int subcategoryid, int typeid,
			HttpServletRequest request) {
		logger.info("***** GET PROJECT AREA BY ID *****");
		List<ProjectArea> projectArea = projectDAO.getProjectAreaForPropertyById(projectid, categoryid, subcategoryid,
				typeid);
		return projectArea;
	}

	@RequestMapping(value = "/getProjectAreaForPropertyByIdAndUnitId", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectArea> getProjectAreaForPropertyByIdAndUnitId(int projectid, int categoryid, int subcategoryid,
			int typeid, int unitmasterid, HttpServletRequest request) {
		logger.info("***** GET PROJECT AREA BY ID *****");
		List<ProjectArea> projectArea = projectDAO.getProjectAreaForPropertyById(projectid, categoryid, subcategoryid,
				typeid, unitmasterid);
		return projectArea;
	}

	@RequestMapping(value = "/getProjectUnitPriceForPropertyById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid, HttpServletRequest request) {
		logger.info("***** GET PROJECT AREA BY ID *****");
		List<ProjectPriceInfo> projectPriceInfo = projectDAO.getProjectUnitPriceForPropertyById(projectid, categoryid,
				subcategoryid, typeid);
		return projectPriceInfo;
	}

	@RequestMapping(value = "/getProjectUnitPriceForPropertyByIdAndUnitId", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyByIdAndUnitId(int projectid, int categoryid,
			int subcategoryid, int typeid, int unitmasterid, HttpServletRequest request) {
		logger.info("***** GET PROJECT AREA BY ID *****");
		List<ProjectPriceInfo> projectPriceInfo = projectDAO.getProjectUnitPriceForPropertyById(projectid, categoryid,
				subcategoryid, typeid, unitmasterid);
		return projectPriceInfo;
	}

	@RequestMapping(value = "/getUnitNameList", method = RequestMethod.GET, produces = "application/json")
	public List<UnitMaster> getUnitNameList(HttpServletRequest request) {
		logger.info("***** GET PROJECTS *****");
		List<UnitMaster> unitMaster = projectDAO.getUnitNameList();
		return unitMaster;
	}

	@RequestMapping(value = "addUnitMaster", method = RequestMethod.POST)
	public String addUnitMaster(String unitname, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD UNIT MASTER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		unitMaster = new UnitMaster(unitname, s, id, IpAddress);
		projectDAO.addUnitMaster(unitMaster);
		return "Success";
	}
	
	@RequestMapping(value = "editProjectBank", method = RequestMethod.POST)
    public String editProjectBank(int projectid, int bankid, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT PROJECT BANKS *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        
        String bankTitle = null;
                
        ProjectBank b = new ProjectBank();
        b.setBankId(bankid);
        b.setProjectId(projectid);
        b.setTitle(bankTitle);
        b.setCreatedBy(id);
        b.setIpAddress(IpAddress);
        
        projectDAO.addProjectBank(b);

        return "Success";
    }

	@RequestMapping(value = "deleteProjectBank", method = RequestMethod.DELETE)
    public void deleteProjectBank(int projectbankid) {
        logger.info("***** DELETE PROJECT BANK *****");
        projectDAO.deleteProjectBank(projectbankid);
    }
	
} //end
