/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

package org.eclipse.jdt.launching;import java.io.File;
import java.net.URL;

/**
 * Represents a particular installation of a VM. A VM instance holds all parameters
 * specific to a VM installation. Unlike VM types, VM instances can be created and
 * configured dynamically at run-time. This is typically done by the user 
 * interactively in the UI.
 * <p>
 * A VM install is responsible for creating VM runners to launch a Java program
 * in a specific mode.
 * </p>
 * <p>
 * This interface is intended to be implemented by clients that contribute
 * to the <code>"org.eclipse.jdt.launching.vmType"</code> extension point.
 * </p>
 */
public interface IVMInstall {
	/**
	 * Returns a VM runner that runs this installed VM in the given mode.
	 * 
	 * @param mode the mode the VM should be launched in; one of the constants
	 *   declared in <code>org.eclipse.debug.core.ILaunchManager</code>
	 * @return 	a VMRunner for a given mode May return <code>null</code> if the given mode
	 * 			is not supported by this VM.
	 * @see org.eclipse.debug.core.ILaunchManager
	 */
	IVMRunner getVMRunner(String mode);
	/**
	 * Returns the id for this VM. VM ids are unique within the VMs 
	 * of a given VM type. The VM id is not intended to be presented to users.
	 * 
	 * @return the VM identifier. Must not return <code>null</code>.
	 */
	String getId();
	/**
	 * Returns the display name of this VM.
	 * The VM name is intended to be presented to users.
	 * 
	 * @return the display name of this VM. May return <code>null</code>.
	 */
	String getName();
	/**
	 * Sets the display name of this VM.
	 * The VM name is intended to be presented to users.
	 * 
	 * @param name the display name of this VM
	 */
	void setName(String name);
	/**
	 * Returns the root directory of the install location of this VM.
	 * 
	 * @return the root directory of this VM installation. May
	 * 			return <code>null</code>.
	 */
	File getInstallLocation();
	/**
	 * Sets the root directory of the install location of this VM.
	 * 
	 * @param installLocation the root directory of this VM installation
	 */
	void setInstallLocation(File installLocation);
		
	/**
	 * Returns the VM type of this VM.
	 * 
	 * @return the VM type that created this IVMInstall instance
	 */
	IVMInstallType getVMInstallType();
	
	/**
	 * Returns the library locations of this IVMInstall. Generally,
	 * clients should use <code>JavaRuntime.getLibraryLocations(IVMInstall)</code>
	 * to determine the libraries associated with this VM install.
	 * 
	 * @see IVMInstall#setLibraryLocations(LibraryLocation[])
	 * @return 	The library locations of this IVMInstall.
	 * 			Returns <code>null</code> to indicate that this VM install uses
	 * 			the default library locations associated with this VM's install type.
	 * @since 2.0
	 */
	LibraryLocation[] getLibraryLocations();	
	
	/**
	 * Sets the library locations of this IVMInstall.
	 * @param	locations The <code>LibraryLocation</code>s to associate
	 * 			with this IVMInstall.
	 * 			May be <code>null</code> to indicate that this VM install uses
	 * 			the default library locations associated with this VM's install type.
	 * @since 2.0
	 */
	void setLibraryLocations(LibraryLocation[] locations);	
	
	/**
	 * Sets the Javadoc location associated with this VM install.
	 * 
	 * @param url a url pointing to the Javadoc location associated with
	 * 	this VM install
	 * @since 2.0
	 */
	public void setJavadocLocation(URL url);
	
	/**
	 * Returns the Javadoc location associated with this VM install.
	 * 
	 * @return a url pointing to the Javadoc location associated with
	 * 	this VM install, or <code>null</code> if none
	 * @since 2.0
	 */
	public URL getJavadocLocation();	
}
