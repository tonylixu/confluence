import com.atlassian.confluence.pages.Page
import com.atlassian.confluence.pages.PageManager
import com.atlassian.confluence.spaces.SpaceManager
import com.atlassian.sal.api.component.ComponentLocator
import org.apache.log4j.Logger
import org.apache.log4j.Level

def log = Logger.getLogger("com.onresolve.jira.groovy")
log.setLevel(Level.INFO)

def spaceManager = ComponentLocator.getComponent(SpaceManager)
def pageManager = ComponentLocator.getComponent(PageManager)

// Retrieve all the spaces
def spaces = spaceManager.allSpaces.findAll()

for (space in spaces) {
    space_name = space['key']
    log.info("spaces is " + space_name)
	// Retrieve space
	def space = spaceManager.getSpace(space_name)
	// Retrieve all the pages in a given space
	def pages = pageManager.getPages(space, true).findAll{it instanceof Page}
	for (page in pages) {
	    log.info("process page: " + page.title)
		// Check if the page has view permission
		ContentPermissionSet viewPermit = page
                    .getContentPermissionSet(ContentPermission.VIEW_PERMISSION);
		log.info("view permission is: " + viewPermit)
		//page.removeContentPermissionSet(viewPerms)
    
		// Check if the page has edit permission
		ContentPermissionSet editPermit = page
                    .getContentPermissionSet(ContentPermission.EDIT_PERMISSION);
		log.info("edit permission is: " + editPermit)
		//page.removeContentPermissionSet(editPerms)
    
		// Set the view permission
		if (viewPermit == null) {
			def permissionManager = ComponentLocator.getComponent(ContentPermissionManager)
			def editRestriction = ContentPermission.createGroupPermission(ContentPermission.VIEW_PERMISSION,"group-name")
			permissionManager.addContentPermission(editRestriction, page)
		}
	}
}
