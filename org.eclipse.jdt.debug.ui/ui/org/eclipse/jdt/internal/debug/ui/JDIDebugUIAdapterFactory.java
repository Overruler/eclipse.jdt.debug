package org.eclipse.jdt.internal.debug.ui;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
 
import java.io.IOException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jdt.launching.sourcelookup.ArchiveSourceLocation;
import org.eclipse.jdt.launching.sourcelookup.DirectorySourceLocation;
import org.eclipse.jdt.launching.sourcelookup.IJavaSourceLocation;
import org.eclipse.jdt.launching.sourcelookup.JavaProjectSourceLocation;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * Workbench properties adapter for source locations
 */
/*package*/ class JDIDebugUIAdapterFactory implements IAdapterFactory {

	class SourceLocationPropertiesAdapter implements IWorkbenchAdapter {
		
		private JavaElementLabelProvider fJavaElementLabelProvider = new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_BASICS);
	
		/**
		 * @see IWorkbenchAdapter#getChildren(Object)
		 */
		public Object[] getChildren(Object o) {
			return new Object[0];
		}

		/**
		 * @see IWorkbenchAdapter#getImageDescriptor(Object)
		 */
		public ImageDescriptor getImageDescriptor(Object o) {
			if (o instanceof JavaProjectSourceLocation) {
				return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_PROJECT);
			} else if (o instanceof DirectorySourceLocation) {
				return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
			} else if (o instanceof ArchiveSourceLocation) {
				return JavaUI.getSharedImages().getImageDescriptor(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_JAR);
			}
			return null;
		}

		/**
		 * @see IWorkbenchAdapter#getLabel(Object)
		 */
		public String getLabel(Object o) {
			if (o instanceof JavaProjectSourceLocation) {
				return fJavaElementLabelProvider.getText(((JavaProjectSourceLocation)o).getJavaProject());
			} else if (o instanceof DirectorySourceLocation) {
				try {
					return ((DirectorySourceLocation)o).getDirectory().getCanonicalPath();
				} catch (IOException e) {
					JDIDebugUIPlugin.log(e);
					return ((DirectorySourceLocation)o).getDirectory().getName();
				}
			} else if (o instanceof ArchiveSourceLocation) {
				return ((ArchiveSourceLocation)o).getName();
			}
			return null;
		}

		/**
		 * @see IWorkbenchAdapter#getParent(Object)
		 */
		public Object getParent(Object o) {
			return null;
		}
	}
	
	/**
	 * @see IAdapterFactory#getAdapter(Object, Class)
	 */
	public Object getAdapter(Object obj, Class adapterType) {
		if (adapterType.isInstance(obj)) {
			return obj;
		}
		if (adapterType == IWorkbenchAdapter.class) {
			if (obj instanceof IJavaSourceLocation) {
				return new SourceLocationPropertiesAdapter();
			}
		}
		return null;
	}

	/**
	 * @see IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[] {
			IWorkbenchAdapter.class
		};
	}
}


