package org.omg.PortableInterceptor;


/**
* org/omg/PortableInterceptor/ObjectReferenceFactory.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /scratch/jenkins/workspace/8-2-build-linux-amd64/jdk8u241/331/corba/src/share/classes/org/omg/PortableInterceptor/Interceptors.idl
* Wednesday, December 11, 2019 2:23:52 AM PST
*/


/** The object reference factory.  This provides the capability of
  * creating an object reference.
  */
public interface ObjectReferenceFactory extends org.omg.CORBA.portable.ValueBase
{

  /** make_object creates a CORBA object reference according
      * to the data in this template, with the given repository ID and
      * object ID.
      */
  public abstract org.omg.CORBA.Object make_object (String repositoryId, byte[] object_id);

} // interface ObjectReferenceFactory
